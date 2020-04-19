package com.avatarduel.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.avatarduel.Constants;
import com.avatarduel.model.CardPosition;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.ArenaCharacterCard;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.skill.SkillCard;
import com.avatarduel.repository.AuraSkillCardRepository;
import com.avatarduel.repository.CharacterCardRepository;
import com.avatarduel.repository.DestroySkillCardRepository;
import com.avatarduel.repository.LandCardRepository;
import com.avatarduel.repository.PowerUpSkillCardRepository;

/**
 * Control a player
 */
public class PlayerController {

    private static final int FIRST_CARD_DRAWN = 7;

    private Player player1, player2, turn;
    private Card clickedCard;
    private LandCardRepository landCardRepository;
    private CharacterCardRepository characterCardRepository;
    private AuraSkillCardRepository auraSkillCardRepository;
    private DestroySkillCardRepository destroySkillCardRepository;
    private PowerUpSkillCardRepository powerUpSkillCardRepository;

    /**
     * Constructor
     *
     * @param player1 Player 1
     * @param player2 Player 2
     * @param landCardRepository Repository for Land Card
     * @param characterCardRepository Repository for Character Card
     * @param auraSkillCardRepository Repository for Aura Skill Card
     * @param destroySkillCardRepository Repository for Destroy Skill Card
     * @param powerUpSkillCardRepository Repository for Power Up Skill Card
     */
    public PlayerController(Player player1, Player player2, LandCardRepository landCardRepository,
            CharacterCardRepository characterCardRepository, AuraSkillCardRepository auraSkillCardRepository,
            DestroySkillCardRepository destroySkillCardRepository,
            PowerUpSkillCardRepository powerUpSkillCardRepository) {
        this.player1 = player1;
        this.player2 = player2;
        this.landCardRepository = landCardRepository;
        this.characterCardRepository = characterCardRepository;
        this.auraSkillCardRepository = auraSkillCardRepository;
        this.destroySkillCardRepository = destroySkillCardRepository;
        this.powerUpSkillCardRepository = powerUpSkillCardRepository;

        player1.addToDeck(getRandomDeck(24, 24, 4, 4, 4));
        player2.addToDeck(getRandomDeck(24, 24, 4, 4, 4));

        player1.drawCard(FIRST_CARD_DRAWN);
        player2.drawCard(FIRST_CARD_DRAWN);

        turn = player1;
    }

    /**
     * Get a randomized deck with a specified number of each type
     *
     * @param landCount      number of land cards for the deck
     * @param characterCount number of character cards for the deck
     * @param auraCount      number of aura cards for the deck
     * @param destroyCount   number of destroy cards for the deck
     * @param powerupCount   number of power up cards for the deck
     * @return randomized list of cards with each specified number of type
     */
    public List<Card> getRandomDeck(int landCount, int characterCount, int auraCount, int destroyCount,
            int powerupCount) {
        List<Card> res = new ArrayList<>();

        res.addAll(landCardRepository.getRandomCards(landCount));
        res.addAll(characterCardRepository.getRandomCards(characterCount));
        res.addAll(auraSkillCardRepository.getRandomCards(auraCount));
        res.addAll(destroySkillCardRepository.getRandomCards(destroyCount));
        res.addAll(powerUpSkillCardRepository.getRandomCards(powerupCount));

        Collections.shuffle(res);

        return res;
    }

    /**
     * Remove specified card from the field
     *
     * @param card specified card
     */
    public void removeCardFromField(ArenaCharacterCard card) {
        int i = 0;
        for (int j = 0; j < Constants.CARD_COLUMN; ++j) {
            if (player1.getFieldCard(i, j) == card) {
                for (SkillCard next : card.getSkillCardList()) {
                    removeCardFromField(next, false);
                }
                player1.removeFieldCard(i, j);
                return;
            } else if (player2.getFieldCard(i, j) == card) {
                for (SkillCard next : card.getSkillCardList()) {
                    removeCardFromField(next, false);
                }
                player2.removeFieldCard(i, j);
                return;
            }
        }
    }

    /**
     * Remove specified card from the field
     *
     * @param card specified card
     */
    public void removeCardFromField(SkillCard card) {
        removeCardFromField(card, true);
    }

    /**
     * Remove specified skill card from the field
     *
     * @param card                specified card
     * @param checkCharacterSkill character skill flag
     */
    public void removeCardFromField(SkillCard card, boolean checkCharacterSkill) {
        for (int i = 0; i < Constants.CARD_ROW; ++i) {
            for (int j = 0; j < Constants.CARD_COLUMN; ++j) {
                Card c = player1.getFieldCard(i, j);
                if (c instanceof ArenaCharacterCard && checkCharacterSkill) {
                    ((ArenaCharacterCard) c).removeSkill(card);
                } else if (c == card) {
                    player1.removeFieldCard(i, j);
                    return;
                }

                c = player2.getFieldCard(i, j);
                if (c instanceof ArenaCharacterCard && checkCharacterSkill) {
                    ((ArenaCharacterCard) c).removeSkill(card);
                } else if (c == card) {
                    player2.removeFieldCard(i, j);
                    return;
                }
            }
        }
    }

    /**
     * @param card the clicked card
     */
    public void setClickedCard(Card card) {
        if (clickedCard != null) {
            clickedCard.setClicked(false);
        }
        clickedCard = card;
        if (card != null) {
            card.setClicked(true);
        }
    }

    /**
     * @return this clickedCard
     */
    public Card getClickedCard() {
        return clickedCard;
    }

    /**
     * @return false if cant do draw phase, true otherwise
     */
    public boolean doDrawPhase() {
        if (turn.getCurrentDeckCount() <= 0) {
            return false;
        }
        turn.drawCard();
        return true;
    }

    /**
     * @return player 1 object
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * @return player 2 object
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * @return player in current turn
     */
    public Player getCurrentPlayerTurn() {
        return turn;
    }

    /**
     * @return enemy in current turn
     */
    public Player getEnemyCurrentTurn() {
        return turn == player1 ? player2 : player1;
    }

    /**
     * Switch turn to other player
     */
    public void switchTurn() {
        turn = turn == player1 ? player2 : player1;
    }

    /**
     * reset turn state
     */
    public void resetPlayerState() {
        turn.resetState();
        setClickedCard(null);
    }

    /**
     * Attack
     *
     * @param attacker attacker card
     * @param defender defender card
     * @return if the attack succesful
     */
    public boolean doAttack(ArenaCharacterCard attacker, ArenaCharacterCard defender) {
        if (defender == null) {
            getEnemyCurrentTurn().damage(attacker.getTotalAttack());
        } else {
            boolean isDefensePosition = defender.getPosition() == CardPosition.DEFENSE;
            int defValue = isDefensePosition ? defender.getTotalDefense() : defender.getTotalAttack();
            int selisih = attacker.getTotalAttack() - defValue;
            if (selisih <= 0) {
                return false;
            }
            removeCardFromField(defender);
            if (!isDefensePosition || attacker.isPoweredUp()) {
                getEnemyCurrentTurn().damage(selisih);
            }
        }
        attacker.setHasAttacked(true);
        return true;
    }

}

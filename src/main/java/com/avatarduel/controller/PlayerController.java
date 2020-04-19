package com.avatarduel.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import com.avatarduel.model.CardPosition;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.ActivableCard;
import com.avatarduel.model.card.ArenaCharacterCard;
import com.avatarduel.model.card.skill.PutableSkillCard;
import com.avatarduel.model.card.skill.SkillCard;
import com.avatarduel.repository.AuraSkillCardRepository;
import com.avatarduel.repository.BaseActivableCardRepository;
import com.avatarduel.repository.CharacterCardRepository;
import com.avatarduel.repository.DestroySkillCardRepository;
import com.avatarduel.repository.PowerUpSkillCardRepository;

/**
 * Control a player
 */
public class PlayerController {

    private Player player1, player2, turn;
    private ActivableCard clickedCard;
    private BaseActivableCardRepository landCardRepository;
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
    public PlayerController(int firstDrawnCardCount, Player player1, Player player2,
            BaseActivableCardRepository landCardRepository, CharacterCardRepository characterCardRepository,
            AuraSkillCardRepository auraSkillCardRepository, DestroySkillCardRepository destroySkillCardRepository,
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

        player1.drawCard(firstDrawnCardCount);
        player2.drawCard(firstDrawnCardCount);

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
    public List<ActivableCard> getRandomDeck(int landCount, int characterCount, int auraCount, int destroyCount,
            int powerupCount) {
        List<ActivableCard> res = new ArrayList<>();

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
        if (player1.removeCharacterCard(card) || player2.removeCharacterCard(card)) {
            for (SkillCard next : card.getSkillCardList()) {
                if (next instanceof PutableSkillCard) {
                    removeCardFromField((PutableSkillCard) next, false);
                }
            }
        }
    }

    /**
     * Remove specified card from the field
     *
     * @param card specified card
     */
    public void removeCardFromField(PutableSkillCard card) {
        removeCardFromField(card, true);
    }

    /**
     * Remove specified skill card from the field
     *
     * @param skillCard                specified card
     * @param checkCharacterSkill character skill flag
     */
    private void removeCardFromField(PutableSkillCard skillCard, boolean checkCharacterSkill) {
        if (checkCharacterSkill) {
            Stream.concat(player1.getCharacterCards().stream(), player2.getCharacterCards().stream())
                    .filter(acc -> acc != null).forEach(acc -> acc.removeSkill(skillCard));
        }
        player1.removeSkillCard(skillCard);
        player2.removeSkillCard(skillCard);
    }

    /**
     * @param card the clicked card
     */
    public void setClickedCard(ActivableCard card) {
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
    public ActivableCard getClickedCard() {
        return clickedCard;
    }

    /**
     * @return false if cant do draw phase, true otherwise
     */
    public boolean doDrawPhase() {
        resetPlayerState();
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

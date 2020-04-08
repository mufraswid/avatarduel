package com.avatarduel.controller;

import com.avatarduel.Constants;
import com.avatarduel.model.CardPosition;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.ActivableCard;
import com.avatarduel.model.card.ActiveCharacterCard;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.skill.SkillCard;

public class PlayerController {

    private Player player1, player2, turn;
    private Card clickedCard;

    public PlayerController(CardDao cardDao) {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");

        player1.addToDeck(cardDao.getRandomDeck(24, 24, 4, 4, 4));
        player2.addToDeck(cardDao.getRandomDeck(24, 24, 4, 4, 4));

        player1.drawCard(Constants.FIRST_CARD_DRAWN);
        player2.drawCard(Constants.FIRST_CARD_DRAWN);

        turn = player1;
    }

    public void removeCardFromField(ActivableCard card) {
        removeCardFromField(card, true);
    }

    public void removeCardFromField(ActivableCard card, boolean checkCharacterSkill) {
        if (card instanceof ActiveCharacterCard) {
            ActiveCharacterCard acc = (ActiveCharacterCard) card;
            int i = 0;
            for (int j = 0; j < Constants.CARD_COLUMN; ++j) {
                if (player1.getFieldCard(i, j) == card) {
                    for (SkillCard next : acc.getSkillCardList()) {
                        if (next instanceof ActivableCard) {
                            removeCardFromField((ActivableCard) next, false);
                        }
                    }
                    player1.removeFieldCard(i, j);
                    return;
                } else if (player2.getFieldCard(i, j) == card) {
                    for (SkillCard next : acc.getSkillCardList()) {
                        if (next instanceof ActivableCard) {
                            removeCardFromField((ActivableCard) next, false);
                        }
                    }
                    player2.removeFieldCard(i, j);
                    return;
                }
            }
        } else if (card instanceof SkillCard) {
            for (int i = 0; i < Constants.CARD_ROW; ++i) {
                for (int j = 0; j < Constants.CARD_COLUMN; ++j) {
                    Card c = player1.getFieldCard(i, j);
                    if (c instanceof ActiveCharacterCard && checkCharacterSkill) {
                        ((ActiveCharacterCard) c).removeSkill((SkillCard) card);
                    } else if (c == card) {
                        player1.removeFieldCard(i, j);
                        return;
                    }

                    c = player2.getFieldCard(i, j);
                    if (c instanceof ActiveCharacterCard && checkCharacterSkill) {
                        ((ActiveCharacterCard) c).removeSkill((SkillCard) card);
                    } else if (c == card) {
                        player2.removeFieldCard(i, j);
                        return;
                    }
                }
            }
        }
    }

    public void setClickedCard(Card card) {
        if (clickedCard != null) {
            clickedCard.setClicked(false);
        }
        clickedCard = card;
        if (card != null) {
            card.setClicked(true);
        }
    }

    public Card getClickedCard() {
        return clickedCard;
    }

    public boolean doDrawPhase() {
        if (turn.getCurrentDeckCount() <= 0) {
            return false;
        }
        turn.drawCard();
        return true;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayerTurn() {
        return turn;
    }

    public Player getEnemyCurrentTurn() {
        return turn == player1 ? player2 : player1;
    }

    public void switchTurn() {
        turn = turn == player1 ? player2 : player1;
    }

    public void resetPlayerState() {
        turn.resetState();
        setClickedCard(null);
    }

    public boolean doAttack(ActiveCharacterCard attacker, ActiveCharacterCard defender) {
        if (defender == null) {
            getEnemyCurrentTurn().damage(attacker.getTotalAttack());
        } else {
            boolean isDefensePosition = defender.getPosition() == CardPosition.DEFENSE;
            int defValue = isDefensePosition ? defender.getTotalDefense() : defender.getTotalAttack();
            int selisih = attacker.getTotalAttack() - defValue;
            if (selisih < 0) {
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
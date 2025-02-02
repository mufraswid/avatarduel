package com.avatarduel.controller;

import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.CardPosition;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.ActivableCard;
import com.avatarduel.model.card.ArenaCharacterCard;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.skill.DestroySkillCard;
import com.avatarduel.model.card.skill.PutableSkillCard;
import com.avatarduel.model.card.skill.SkillCard;

import javafx.scene.Cursor;

/**
 * Listen for event that happened to cards on field
 */
public class CardFieldEventListener implements CardEventListener {

    private GameController gameController;

    /**
     * Constructor
     *
     * @param gameController the current game
     */
    public CardFieldEventListener(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Handle if cursor hover above card
     *
     * @param card the hovered card
     */
    @Override
    public void onMouseEntered(Card card) {
        gameController.getRenderController().setLastTouchedCard(card);
        gameController.getRenderController().getScene().setCursor(Cursor.HAND);
    }

    /**
     * Handle if cursor hover out of card
     *
     * @param card the hovered card
     */
    @Override
    public void onMouseExited(Card card) {
        gameController.getRenderController().getScene().setCursor(Cursor.DEFAULT);
    }

    /**
     * Handle if a card got right clicked
     *
     * @param c the clicked card
     */
    @Override
    public void onMouseRightClicked(Card c) {
        Phase phase = gameController.getPhase();
        if (phase == Phase.DRAW || phase == Phase.END) {
            return;
        }
        if (!(c instanceof ActivableCard)) {
            return;
        }
        ActivableCard card = (ActivableCard) c;
        RenderController renderController = gameController.getRenderController();
        PlayerController playerController = gameController.getPlayerController();
        if (card == renderController.getClosedCard()) {
            return;
        }
        Player turn = playerController.getCurrentPlayerTurn();
        if (turn.hasCardOnField(card)) {
            if (phase != Phase.BATTLE && (card instanceof ArenaCharacterCard || card instanceof PutableSkillCard)) {
                if (card instanceof ArenaCharacterCard) {
                    playerController.removeCardFromField((ArenaCharacterCard) card);
                } else {
                    playerController.removeCardFromField((PutableSkillCard) card);
                }
                renderController.updateFieldCard(turn);
                renderController.updateFieldCard(playerController.getEnemyCurrentTurn());
            }
        }
    }

    /**
     * Handle if a card got left clicked
     *
     * @param c the clicked card
     */
    @Override
    public void onMouseLeftClicked(Card c) {
        Phase phase = gameController.getPhase();
        if (phase == Phase.DRAW || phase == Phase.END) {
            return;
        }
        if (!(c instanceof ActivableCard)) {
            return;
        }
        ActivableCard card = (ActivableCard) c;
        RenderController renderController = gameController.getRenderController();
        PlayerController playerController = gameController.getPlayerController();
        if (card == renderController.getClosedCard()) {
            return;
        }
        Player turn = playerController.getCurrentPlayerTurn();
        ActivableCard clicked = playerController.getClickedCard();
        if (card instanceof ArenaCharacterCard) {
            ArenaCharacterCard acc = (ArenaCharacterCard) card;
            boolean turnsCard = turn.hasCardOnField(acc);
            Player enemy = playerController.getEnemyCurrentTurn();
            if (phase == Phase.BATTLE) {
                if (turnsCard && acc.getPosition() == CardPosition.ATTACK && !acc.hasAttacked()
                        && acc.isEnableToAttack()) {
                    if (enemy.hasAnyArenaCharacterCard()) {
                        playerController.setClickedCard(card == clicked ? null : card);
                        renderController.updateFieldCard(turn);
                    } else {
                        playerController.doAttack(acc, null);
                        renderController.updateStatus(enemy);
                        gameController.checkEndGame();
                    }
                } else if (!turnsCard && clicked instanceof ArenaCharacterCard) {
                    ArenaCharacterCard accClicked = (ArenaCharacterCard) clicked;
                    if (turn.hasCardOnField(accClicked) && playerController.doAttack(accClicked, acc)) {
                        renderController.updateFieldCard(enemy);
                        renderController.updateStatus(enemy);
                        gameController.checkEndGame();
                        playerController.setClickedCard(null);
                        renderController.updateFieldCard(turn);
                    }
                }
            } else {
                if (clicked instanceof SkillCard) {
                    if (clicked.canBePutOn(turn)) {
                        clicked.putOn(turn);
                        playerController.setClickedCard(null);
                        if (clicked instanceof DestroySkillCard) {
                            playerController.removeCardFromField(acc);
                        } else {
                            acc.addSkill((SkillCard) clicked);
                        }
                        renderController.updateHandCard(turn);
                        renderController.updateFieldCard(turn);
                        renderController.updateFieldCard(enemy);
                    }
                } else if (clicked != null) {
                    playerController.setClickedCard(null);
                    renderController.updateFieldCard(turn);
                } else if (turnsCard && !acc.hasAttacked()) {
                    acc.switchPosition();
                    renderController.updateFieldCard(turn);
                }
            }
        }
    }

}

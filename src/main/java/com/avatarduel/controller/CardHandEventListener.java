package com.avatarduel.controller;

import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.skill.SkillCard;

import javafx.scene.Cursor;

public class CardHandEventListener implements CardEventListener {

    private GameController gameController;

    public CardHandEventListener(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void onMouseEntered(Card card) {
        gameController.getRenderController().setLastTouchedCard(card);
        gameController.getRenderController().getScene().setCursor(Cursor.HAND);
    }

    @Override
    public void onMouseExited(Card card) {
        gameController.getRenderController().getScene().setCursor(Cursor.DEFAULT);
    }

    @Override
    public void onMouseRightClicked(Card card) {
    }

    @Override
    public void onMouseLeftClicked(Card card) {
        Phase phase = gameController.getPhase();
        if (phase != Phase.MAIN1 && phase != Phase.MAIN2) {
            return;
        }
        RenderController renderController = gameController.getRenderController();
        PlayerController playerController = gameController.getPlayerController();
        if (card == renderController.getClosedCard()) {
            return;
        }
        Player turn = playerController.getCurrentPlayerTurn();
        Card clicked = playerController.getClickedCard();
        if (card instanceof SkillCard) {
            Card next = card != clicked && turn.canPutCard(card) ? card : null;
            playerController.setClickedCard(next);
            if (next != clicked) {
                renderController.updateHandCard(turn);
            }
        } else {
            playerController.setClickedCard(null);
            if (turn.canPutCard(card)) {
                turn.putCard(card);
                renderController.updateFieldCard(turn);
                renderController.updateElementValues(turn);
                renderController.updateHandCard(turn);
            } else if (clicked != null) {
                renderController.updateHandCard(turn);
            }
        }
    }

}
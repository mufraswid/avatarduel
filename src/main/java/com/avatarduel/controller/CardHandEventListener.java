package com.avatarduel.controller;

import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;

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
        RenderController renderController = gameController.getRenderController();
        if (card == renderController.getClosedCard()) {
            return;
        }
        Player turn = gameController.getPlayerController().getCurrentPlayerTurn();
        turn.putCard(card);
        renderController.updateFieldCard(turn);
        renderController.updateHandCard(turn);
        renderController.updateElementValues(turn);
    }

}
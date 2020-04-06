package com.avatarduel.controller;

import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.card.Card;

import javafx.scene.Cursor;

public class CardFieldEventListener implements CardEventListener {

    private GameController gameController;

    public CardFieldEventListener(GameController gameController) {
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
        // TODO Auto-generated method stub

    }

    @Override
    public void onMouseLeftClicked(Card card) {
        // TODO Auto-generated method stub

    }

}
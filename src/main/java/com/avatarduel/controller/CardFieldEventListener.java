package com.avatarduel.controller;

import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.card.Card;

import javafx.scene.Cursor;

public class CardFieldEventListener implements CardEventListener {

    private GameController gameController;
    private RenderController renderController;

    public CardFieldEventListener(GameController gameController, RenderController renderController) {
        this.gameController = gameController;
        this.renderController = renderController;
    }

    @Override
    public void onMouseEntered(Card card) {
        renderController.setLastTouchedCard(card);
        renderController.getScene().setCursor(Cursor.HAND);
    }

    @Override
    public void onMouseExited(Card card) {
        renderController.getScene().setCursor(Cursor.DEFAULT);
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
package com.avatarduel.controller;

import com.avatarduel.controller.listener.MouseEventListener;

import javafx.scene.Cursor;

public class PhaseEventListener implements MouseEventListener {

    private GameController gameController;

    public PhaseEventListener(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void onMouseEntered() {
        gameController.getRenderController().getScene().setCursor(Cursor.HAND);
    }

    @Override
    public void onMouseExited() {
        gameController.getRenderController().getScene().setCursor(Cursor.DEFAULT);
    }

    @Override
    public void onMouseRightClicked() {
    }

    @Override
    public void onMouseLeftClicked() {
        gameController.nextPhase();
    }

}
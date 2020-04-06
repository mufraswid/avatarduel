package com.avatarduel.controller;

import com.avatarduel.controller.listener.MouseEventListener;

import javafx.scene.Cursor;

public class PhaseEventListener implements MouseEventListener {

    private GameController gameController;
    private RenderController renderController;

    public PhaseEventListener(GameController gameController, RenderController renderController) {
        this.gameController = gameController;
        this.renderController = renderController;
    }

    @Override
    public void onMouseEntered() {
        renderController.getScene().setCursor(Cursor.HAND);
    }

    @Override
    public void onMouseExited() {
        renderController.getScene().setCursor(Cursor.DEFAULT);
    }

    @Override
    public void onMouseRightClicked() {
    }

    @Override
    public void onMouseLeftClicked() {
        gameController.nextPhase();
    }

}
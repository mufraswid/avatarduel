package com.avatarduel.controller;

import com.avatarduel.controller.listener.MouseEventListener;

import javafx.scene.Cursor;

/**
 * Control how user interact with phase view
 */
public class PhaseEventListener implements MouseEventListener {

    private GameController gameController;

    /**
     * Constructor
     *
     * @param gameController the current game
     */
    public PhaseEventListener(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Change cursor type when hovered above phase view
     */
    @Override
    public void onMouseEntered() {
        gameController.getRenderController().getScene().setCursor(Cursor.HAND);
    }

    /**
     * Change cursor type when hovered out of phase view
     */
    @Override
    public void onMouseExited() {
        gameController.getRenderController().getScene().setCursor(Cursor.DEFAULT);
    }

    /**
     * Handle what happen when right click event happened
     */
    @Override
    public void onMouseRightClicked() {
    }

    /**
     * Handle what happen when left click event happened
     */
    @Override
    public void onMouseLeftClicked() {
        gameController.nextPhase();
    }

}

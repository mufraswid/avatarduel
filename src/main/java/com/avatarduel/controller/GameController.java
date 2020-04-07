package com.avatarduel.controller;

import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

public class GameController {

    private Phase phase;

    private RenderController renderController;
    private PlayerController playerController;

    public GameController(CardDao cardDao) {
        playerController = new PlayerController(cardDao);
        phase = Phase.DRAW;
        renderController = new RenderController(playerController.getPlayer1(), playerController.getPlayer2(),
                new CardHandEventListener(this), new CardFieldEventListener(this), new PhaseEventListener(this));
        playPhase();
    }

    public RenderController getRenderController() {
        return renderController;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void nextPhase() {
        if (phase.ordinal() == Phase.values().length - 1) {
            phase = Phase.values()[0];
            playerController.switchTurn();
        } else {
            phase = Phase.values()[phase.ordinal() + 1];
        }
        playPhase();
    }

    public Phase getPhase() {
        return phase;
    }

    public void playPhase() {
        renderController.updatePhase(phase);
        switch (phase) {
            case DRAW: {
                playerController.resetPlayerState();
                if (!playerController.doDrawPhase()) {
                    endGame(playerController.getEnemyCurrentTurn());
                    return;
                }
                Player turn = playerController.getCurrentPlayerTurn();
                renderController.updateElementValues(turn);
                renderController.updateHandCard(turn);
                renderController.updateDeckCount(turn);
                break;
            }
            case MAIN1: {

                break;
            }
            case BATTLE: {

                break;
            }
            case MAIN2: {

                break;
            }
            case END: {

                break;
            }
        }
    }

    public void endGame(Player winner) {
        Alert alert = new Alert(AlertType.INFORMATION, winner.getName() + " win!", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
        Platform.exit();
    }

}
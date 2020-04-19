package com.avatarduel.controller;

import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.ClosedCard;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

/**
 * GameController is where the flow of the game is controlled
 */
public class GameController {

    private Phase phase;

    private RenderController renderController;
    private PlayerController playerController;

    /**
     * Constructor
     *
     * @param playerController player controller that will be controlling in this game
     */
    public GameController(PlayerController playerController) {
        this.playerController = playerController;
        phase = Phase.DRAW;
        renderController = new RenderController(playerController.getPlayer1(), playerController.getPlayer2(),
                new ClosedCard(), new CardHandEventListener(this), new CardFieldEventListener(this),
                new PhaseEventListener(this));
        playPhase();
    }

    /**
     * @return this render controller
     */
    public RenderController getRenderController() {
        return renderController;
    }

    /**
     * @return this player controller
     */
    public PlayerController getPlayerController() {
        return playerController;
    }

    /**
     * Move to next phase, switch turn to the other player if already END phase
     */
    public void nextPhase() {
        if (phase.ordinal() == Phase.values().length - 1) {
            phase = Phase.values()[0];
            playerController.switchTurn();
        } else {
            phase = Phase.values()[phase.ordinal() + 1];
        }
        playPhase();
    }

    /**
     * @return this phase
     */
    public Phase getPhase() {
        return phase;
    }

    /**
     * Play the current phase
     */
    public void playPhase() {
        Card clicked = playerController.getClickedCard();
        renderController.updatePhase(phase);
        playerController.setClickedCard(null);
        Player turn = playerController.getCurrentPlayerTurn();
        if (phase == Phase.DRAW) {
            if (!playerController.doDrawPhase()) {
                endGame(playerController.getEnemyCurrentTurn());
                return;
            }
            renderController.updateElementValues(turn);
            renderController.updateHandCard(turn);
            renderController.updateDeckCount(turn);
        } else if (clicked != null) {
            renderController.updateHandCard(turn);
        }
    }

    /**
     * End the game and display the winner
     *
     * @param winner player who win
     */
    public void endGame(Player winner) {
        Alert alert = new Alert(AlertType.INFORMATION, winner.getName() + " win!", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
        Platform.exit();
    }

    /**
     * Check if the game is finished
     */
    public void checkEndGame() {
        Player winner = null;
        Player player1 = playerController.getPlayer1(), player2 = playerController.getPlayer2();
        if (player1.getHP() <= 0) {
            winner = player2;
        } else if (player2.getHP() <= 0) {
            winner = player1;
        }
        if (winner != null) {
            endGame(winner);
        }
    }

}

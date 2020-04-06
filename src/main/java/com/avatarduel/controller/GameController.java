package com.avatarduel.controller;

import com.avatarduel.Constants;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

public class GameController {

    private Player player1, player2, turn;
    private Phase phase;

    private RenderController renderController;

    public GameController(CardDao cardDao) {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");

        player1.addToDeck(cardDao.getRandomDeck(24, 24, 4, 4, 4));
        player2.addToDeck(cardDao.getRandomDeck(24, 24, 4, 4, 4));

        player1.drawCard(Constants.FIRST_CARD_DRAWN);
        player2.drawCard(Constants.FIRST_CARD_DRAWN);

        phase = Phase.DRAW;
        turn = player1;
        renderController = new RenderController(player1, player2, new CardHandEventListener(this),
                new CardFieldEventListener(this), new PhaseEventListener(this));

        // TODO delete later
        // player1.putCard(0, 5, player1.getHandCards().get(0));
        // ActiveCharacterCard ac = ((CharacterCard) player1.getHandCards().stream()
        //         .filter(c -> c instanceof CharacterCard).findAny().get()).createActiveCard();
        // ac.switchPosition();
        // player1.putCard(0, 4, ac);
        // renderController.updateFieldCard(player1);

        playPhase();
    }

    public RenderController getRenderController() {
        return renderController;
    }

    public void nextPhase() {
        if (phase.ordinal() == Phase.values().length - 1) {
            phase = Phase.values()[0];
            turn = turn == player1 ? player2 : player1;
        } else {
            phase = Phase.values()[phase.ordinal() + 1];
        }
        playPhase();
    }

    public Phase getPhase() {
        return phase;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayerTurn() {
        return turn;
    }

    public Player getEnemyCurrentTurn() {
        return turn == player1 ? player2 : player1;
    }

    public void playPhase() {
        renderController.updatePhase(phase);
        switch (phase) {
            case DRAW: {
                if (turn.getCurrentDeckCount() <= 0) {
                    endGame(getEnemyCurrentTurn());
                    return;
                }
                turn.drawCard();
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
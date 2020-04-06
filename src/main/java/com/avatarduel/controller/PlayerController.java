package com.avatarduel.controller;

import com.avatarduel.Constants;
import com.avatarduel.model.Player;

public class PlayerController {

    private Player player1, player2, turn;

    public PlayerController(CardDao cardDao) {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");

        player1.addToDeck(cardDao.getRandomDeck(24, 24, 4, 4, 4));
        player2.addToDeck(cardDao.getRandomDeck(24, 24, 4, 4, 4));

        player1.drawCard(Constants.FIRST_CARD_DRAWN);
        player2.drawCard(Constants.FIRST_CARD_DRAWN);

        turn = player1;
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

    public void switchTurn() {
        turn = turn == player1 ? player2 : player1;
    }

}
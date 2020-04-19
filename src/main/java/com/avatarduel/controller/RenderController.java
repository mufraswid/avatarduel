package com.avatarduel.controller;

import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.controller.listener.MouseEventListener;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.ClosedCard;
import com.avatarduel.view.main.MainView;

import javafx.scene.Scene;

/**
 * This class is used to control the game scene
 */
public class RenderController {

    private static final double WIDTH = 1280;
    private static final double HEIGHT = 720;

    private MainView mainView;
    private Scene scene;
    private Card closedCard;
    private Player player1, player2;

    /**
     * Constructor
     *
     * @param player1                player 1 object
     * @param player2                player 2 object
     * @param handCardEventListener  hand card event listener
     * @param cardFieldEventListener card field event listener
     * @param phaseEventListener     phase event listener
     */
    public RenderController(Player player1, Player player2, CardEventListener handCardEventListener,
            CardEventListener cardFieldEventListener, MouseEventListener phaseEventListener) {
        scene = new Scene(mainView = new MainView(handCardEventListener, cardFieldEventListener, phaseEventListener),
                WIDTH, HEIGHT);
        closedCard = new ClosedCard();
        this.player1 = player1;
        this.player2 = player2;
        initView();
    }

    /**
     * Initialize views
     */
    private void initView() {
        setLastTouchedCard(closedCard);
        mainView.renderCardFieldView1(player1);
        mainView.renderCardFieldView2(player2);
        mainView.renderDeckView1(player1);
        mainView.renderDeckView2(player2);
        mainView.renderElementView1(player1);
        mainView.renderElementView2(player2);
        mainView.renderStatusView1(player1);
        mainView.renderStatusView2(player2);
    }

    /**
     * @param phase current phase
     */
    public void updatePhase(Phase phase) {
        mainView.renderPhase(phase);
    }

    /**
     * @return this scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * @param card the last touched card
     */
    public void setLastTouchedCard(Card card) {
        mainView.renderBigCardView(card);
    }

    /**
     * @return closed card
     */
    public Card getClosedCard() {
        return closedCard;
    }

    /**
     * Update card at hand based on player turns
     *
     * @param turn which player's turn
     */
    public void updateHandCard(Player turn) {
        mainView.renderHandCardView1(player1, turn == player1 ? null : closedCard);
        mainView.renderHandCardView2(player2, turn == player2 ? null : closedCard);
    }

    /**
     * Update field card according to whose turn
     *
     * @param turn which player's turn
     */
    public void updateFieldCard(Player turn) {
        if (turn == player1) {
            mainView.renderCardFieldView1(turn);
        } else if (turn == player2) {
            mainView.renderCardFieldView2(turn);
        }
    }

    /**
     * Update deck counter according to whose turn
     *
     * @param turn which player's turn
     */
    public void updateDeckCount(Player turn) {
        if (turn == player1) {
            mainView.renderDeckView1(turn);
        } else if (turn == player2) {
            mainView.renderDeckView2(turn);
        }
    }

    /**
     * Update element values according to whose turn
     *
     * @param turn which player's turn
     */
    public void updateElementValues(Player turn) {
        if (turn == player1) {
            mainView.renderElementView1(turn);
        } else if (turn == player2) {
            mainView.renderElementView2(turn);
        }
    }

    /**
     * Update player status according to whose turn
     *
     * @param turn which player's turn
     */
    public void updateStatus(Player turn) {
        if (turn == player1) {
            mainView.renderStatusView1(turn);
        } else if (turn == player2) {
            mainView.renderStatusView2(turn);
        }
    }

}

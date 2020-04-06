package com.avatarduel.controller;

import com.avatarduel.Constants;
import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.controller.listener.MouseEventListener;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.ClosedCard;
import com.avatarduel.view.main.MainView;

import javafx.scene.Scene;

public class RenderController {
    private MainView mainView;
    private Scene scene;
    private Card closedCard;
    private Player player1, player2;

    public RenderController(Player player1, Player player2, CardEventListener handCardEventListener,
            CardEventListener cardFieldEventListener, MouseEventListener phaseEventListener) {
        scene = new Scene(mainView = new MainView(handCardEventListener, cardFieldEventListener, phaseEventListener),
                Constants.WIDTH, Constants.HEIGHT);
        closedCard = new ClosedCard();
        this.player1 = player1;
        this.player2 = player2;
        initView();
    }

    private void initView() {
        mainView.renderBigCardView(closedCard);
        mainView.renderCardFieldView1(player1);
        mainView.renderCardFieldView2(player2);
        mainView.renderDeckView1(player1);
        mainView.renderDeckView2(player2);
        mainView.renderElementView1(player1);
        mainView.renderElementView2(player2);
        mainView.renderHandCardView1(player1);
        mainView.renderHandCardView2(player2);
        mainView.renderStatusView1(player1);
        mainView.renderStatusView2(player2);
    }

    public void updatePhase(Phase phase) {
        mainView.renderPhase(phase);
    }

    public Scene getScene() {
        return scene;
    }

    public void setLastTouchedCard(Card card) {
        mainView.renderBigCardView(card);
    }

    public Card getClosedCard() {
        return closedCard;
    }

    public void updateHandCard(Player turn) {
        if (turn == player1) {
            mainView.renderHandCardView1(turn);
        } else if (turn == player2) {
            mainView.renderHandCardView2(turn);
        }
    }

    public void updateFieldCard(Player turn) {
        if (turn == player1) {
            mainView.renderCardFieldView1(turn);
        } else if (turn == player2) {
            mainView.renderCardFieldView2(turn);
        }
    }

    public void updateDeckCount(Player turn) {
        if (turn == player1) {
            mainView.renderDeckView1(turn);
        } else if (turn == player2) {
            mainView.renderDeckView2(turn);
        }
    }

}
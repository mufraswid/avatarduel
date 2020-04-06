package com.avatarduel.view.main;

import com.avatarduel.Constants;
import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.controller.listener.MouseEventListener;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import com.avatarduel.view.GridView;

import javafx.geometry.Insets;

public class MainView extends GridView {

    private LeftMainView leftMainView;
    private CenterMainView centerMainView;
    private RightMainView rightMainView;

    public MainView(CardEventListener handCardEventListener, CardEventListener cardFieldEventListener,
            MouseEventListener phaseEventListener) {
        super("20,70,10", "100");
        setPadding(new Insets(Constants.GAP, Constants.GAP, Constants.GAP, Constants.GAP));
        add(leftMainView = new LeftMainView(), 0, 0);
        add(centerMainView = new CenterMainView(handCardEventListener, cardFieldEventListener), 1, 0);
        add(rightMainView = new RightMainView(phaseEventListener), 2, 0);
    }

    public void renderDeckView2(Player player) {
        leftMainView.renderDeckView2(player);
    }

    public void renderStatusView2(Player player) {
        leftMainView.renderStatusView2(player);
    }

    public void renderBigCardView(Card card) {
        leftMainView.renderBigCardView(card);
    }

    public void renderStatusView1(Player player) {
        leftMainView.renderStatusView1(player);
    }

    public void renderDeckView1(Player player) {
        leftMainView.renderDeckView1(player);
    }

    public void renderHandCardView2(Player player) {
        centerMainView.renderHandCardView2(player);
    }

    public void renderHandCardView1(Player player) {
        centerMainView.renderHandCardView1(player);
    }

    public void renderCardFieldView2(Player player) {
        centerMainView.renderCardFieldView2(player);
    }

    public void renderCardFieldView1(Player player) {
        centerMainView.renderCardFieldView1(player);
    }

    public void renderElementView2(Player player) {
        rightMainView.renderElementView2(player);
    }

    public void renderPhase(Phase phase) {
        rightMainView.renderPhase(phase);
    }

    public void renderElementView1(Player player) {
        rightMainView.renderElementView1(player);
    }

}
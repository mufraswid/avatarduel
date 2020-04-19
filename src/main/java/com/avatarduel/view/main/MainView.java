package com.avatarduel.view.main;

import com.avatarduel.controller.CardFieldDimension;
import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.controller.listener.MouseEventListener;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import com.avatarduel.view.GridView;

import javafx.geometry.Insets;

/**
 * This class help to organize every section in one place
 */
public class MainView extends GridView {

    private LeftMainView leftMainView;
    private CenterMainView centerMainView;
    private RightMainView rightMainView;

    /**
     * Constructor
     *
     * @param handCardEventListener  specified hand card event listener
     * @param cardFieldEventListener specified card field event listener
     * @param phaseEventListener     specified phase event listener
     */
    public MainView(CardFieldDimension cardFieldDimension1, CardFieldDimension cardFieldDimension2,
            CardEventListener handCardEventListener, CardEventListener cardFieldEventListener,
            MouseEventListener phaseEventListener) {
        super("20,70,10", "100");
        setPadding(new Insets(10, 10, 10, 10));
        add(leftMainView = new LeftMainView(), 0, 0);
        add(centerMainView = new CenterMainView(cardFieldDimension1, cardFieldDimension2, handCardEventListener,
                cardFieldEventListener), 1, 0);
        add(rightMainView = new RightMainView(phaseEventListener), 2, 0);
    }

    /**
     * @param player specified player
     */
    public void renderDeckView2(Player player) {
        leftMainView.renderDeckView2(player);
    }

    /**
     * @param player specified player
     */
    public void renderStatusView2(Player player) {
        leftMainView.renderStatusView2(player);
    }

    /**
     * @param card specified card
     */
    public void renderBigCardView(Card card) {
        leftMainView.renderBigCardView(card);
    }

    /**
     * @param player specified player
     */
    public void renderStatusView1(Player player) {
        leftMainView.renderStatusView1(player);
    }

    /**
     * @param player specified player
     */
    public void renderDeckView1(Player player) {
        leftMainView.renderDeckView1(player);
    }

    /**
     * @param player specified player
     * @param closed closed card object
     */
    public void renderHandCardView2(Player player, Card closed) {
        centerMainView.renderHandCardView2(player, closed);
    }

    /**
     * @param player specified player
     * @param closed closed card object
     */
    public void renderHandCardView1(Player player, Card closed) {
        centerMainView.renderHandCardView1(player, closed);
    }

    /**
     * @param player specified player
     */
    public void renderCardFieldView2(Player player) {
        centerMainView.renderCardFieldView2(player);
    }

    /**
     * @param player specified player
     */
    public void renderCardFieldView1(Player player) {
        centerMainView.renderCardFieldView1(player);
    }

    /**
     * @param player specified player
     */
    public void renderElementView2(Player player) {
        rightMainView.renderElementView2(player);
    }

    /**
     * @param phase specified phase
     */
    public void renderPhase(Phase phase) {
        rightMainView.renderPhase(phase);
    }

    /**
     * @param player specified player
     */
    public void renderElementView1(Player player) {
        rightMainView.renderElementView1(player);
    }

}

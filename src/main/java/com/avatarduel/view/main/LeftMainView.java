package com.avatarduel.view.main;

import com.avatarduel.model.IPlayer;
import com.avatarduel.model.card.Card;
import com.avatarduel.view.GridView;
import com.avatarduel.view.ViewPosition;
import com.avatarduel.view.child.StatusView;
import com.avatarduel.view.child.card.BigCardView;
import com.avatarduel.view.child.card.DeckView;

/**
 * Organize the left panel of the scene
 */
public class LeftMainView extends GridView {

    private DeckView deckView1, deckView2;
    private StatusView statusView1, statusView2;
    private BigCardView bigCardView;

    /**
     * Constructor
     */
    public LeftMainView() {
        super("25,75", "15,70,15");
        add(deckView2 = new DeckView(), 0, 0);
        add(statusView2 = new StatusView(ViewPosition.TOP), 1, 0);
        add(bigCardView = new BigCardView(), 0, 1, 2, 1);
        add(statusView1 = new StatusView(ViewPosition.BOTTOM), 1, 2);
        add(deckView1 = new DeckView(), 0, 2);
    }

    /**
     * @param player specified player
     */
    public void renderDeckView2(IPlayer player) {
        deckView2.render(player);
    }

    /**
     * @param player specified player
     */
    public void renderStatusView2(IPlayer player) {
        statusView2.render(player);
    }

    /**
     * @param card specified card
     */
    public void renderBigCardView(Card card) {
        bigCardView.render(card);
    }

    /**
     * @param player specified player
     */
    public void renderStatusView1(IPlayer player) {
        statusView1.render(player);
    }

    /**
     * @param player specified player
     */
    public void renderDeckView1(IPlayer player) {
        deckView1.render(player);
    }

}

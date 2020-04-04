package com.avatarduel.view.main;

import com.avatarduel.controller.Game;
import com.avatarduel.view.GridView;
import com.avatarduel.view.child.StatusView;
import com.avatarduel.view.child.card.BigCardView;
import com.avatarduel.view.child.card.DeckView;

public class LeftMainView extends GridView {

    private BigCardView bigCardView;
    private DeckView deckView1, deckView2;
    private StatusView statusView1, statusView2;

    public LeftMainView() {
        super("25,75", "15,70,15");
        Game game = Game.getInstance();
        bigCardView = new BigCardView(null);
        deckView1 = new DeckView(game.getPlayer1());
        deckView2 = new DeckView(game.getPlayer2());
        statusView1 = new StatusView(game.getPlayer1());
        statusView2 = new StatusView(game.getPlayer2());
        initGUI();
    }

    public BigCardView getBigCardView() {
        return bigCardView;
    }

    @Override
    public void initGUI() {
        add(deckView2, 0, 0);
        add(statusView2, 1, 0);
        add(bigCardView, 0, 1, 2, 1);
        add(statusView1, 1, 2);
        add(deckView1, 0, 2);
    }

}
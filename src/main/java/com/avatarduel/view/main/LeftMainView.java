package com.avatarduel.view.main;

import com.avatarduel.controller.GameController;
import com.avatarduel.controller.card.CardController;
import com.avatarduel.view.GridView;
import com.avatarduel.view.child.StatusView;
import com.avatarduel.view.child.card.BigCardView;
import com.avatarduel.view.child.card.CardRenderer;
import com.avatarduel.view.child.card.DeckView;

public class LeftMainView extends GridView implements GameRenderer, CardRenderer {

    private BigCardView bigCardView;
    private DeckView deckView1, deckView2;
    private StatusView statusView1, statusView2;

    public LeftMainView() {
        super("25,75", "15,70,15");
        bigCardView = new BigCardView();
        deckView1 = new DeckView();
        deckView2 = new DeckView();
        statusView1 = new StatusView();
        statusView2 = new StatusView();
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

    @Override
    public void renderCard(CardController card) {
        bigCardView.renderCard(card);
    }

    @Override
    public void renderGame(GameController game) {
        deckView1.renderPlayer(game.getPlayer1());
        deckView2.renderPlayer(game.getPlayer2());
        statusView1.renderPlayer(game.getPlayer1());
        statusView2.renderPlayer(game.getPlayer2());
    }

}
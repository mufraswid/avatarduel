package com.avatarduel.view.main;

import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import com.avatarduel.view.GridView;
import com.avatarduel.view.child.StatusView;
import com.avatarduel.view.child.card.BigCardView;
import com.avatarduel.view.child.card.DeckView;

public class LeftMainView extends GridView {

    public LeftMainView(Player player1, Player player2, Card card) {
        super("25,75", "15,70,15");
        add(new DeckView(player2), 0, 0);
        add(new StatusView(player2), 1, 0);
        add(new BigCardView(card), 0, 1, 2, 1);
        add(new StatusView(player1), 1, 2);
        add(new DeckView(player1), 0, 2);
    }

}
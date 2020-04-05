package com.avatarduel.view.child.card;

import com.avatarduel.model.Player;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;

public class DeckView extends GridView {

    public DeckView(Player player) {
        super("100", "50,50");
        addBorder();
        add(new DefaultText("DECK"), 0, 0);
        add(new DefaultText(String.format("%d / %d", player.getCurrentDeckCount(), player.getTotalDeckCount())), 0, 1);
    }

}
package com.avatarduel.view.child.card;

import com.avatarduel.model.Player;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;

public class DeckView extends GridView {

    private DefaultText deckValue;

    public DeckView() {
        super("100", "50,50");
        addBorder();
        add(new DefaultText("DECK"), 0, 0);
        add(deckValue = new DefaultText(), 0, 1);
    }

    public void render(Player player) {
        deckValue.setText(String.format("%d / %d", player.getCurrentDeckCount(), player.getTotalDeckCount()));
    }

}
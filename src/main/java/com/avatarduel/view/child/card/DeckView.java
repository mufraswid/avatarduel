package com.avatarduel.view.child.card;

import com.avatarduel.model.IPlayer;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;

/**
 * Display deck for each player
 */
public class DeckView extends GridView {

    private DefaultText deckValue;

    /**
     * Constructor
     */
    public DeckView() {
        super("100", "50,50");
        addBorder();
        add(new DefaultText("DECK"), 0, 0);
        add(deckValue = new DefaultText(), 0, 1);
    }

    /**
     * @param player specified player
     */
    public void render(IPlayer player) {
        deckValue.setText(String.format("%d / %d", player.getCurrentDeckCount(), player.getTotalDeckCount()));
    }

}

package com.avatarduel.view.child.card;

import com.avatarduel.controller.PlayerController;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;
import com.avatarduel.view.child.PlayerRenderer;

public class DeckView extends GridView implements PlayerRenderer {

    private DefaultText deckValueText, deckText;

    public DeckView() {
        super("100", "50,50");
        this.deckValueText = new DefaultText();
        this.deckText = new DefaultText("DECK");
        initGUI();
    }

    @Override
    public void initGUI() {
        addBorder();
        add(deckText, 0, 0);
        add(deckValueText, 0, 1);
    }

    @Override
    public void renderPlayer(PlayerController player) {
        deckValueText.setText(String.format("%d / %d", player.getCurrentDeckCount(), player.getTotalDeckCount()));
    }

}
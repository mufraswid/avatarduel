package com.avatarduel.view.child.card;

import com.avatarduel.model.Player;
import com.avatarduel.view.DefaultText;

public class DeckView extends CardView {

    private Player player;
    private DefaultText deckValueText;

    public DeckView(Player player) {
        super(null, "100", "100");
        this.player = player;
        this.deckValueText = new DefaultText();

        initGUI();
    }

    @Override
    public void initGUI() {
        add(deckValueText, 0, 0);
        refreshView();
    }

    @Override
    public void refreshView() {
        deckValueText.setText(String.format("%d / %d", player.getCurrentDeckCount(), player.getTotalDeckCount()));
    }

}
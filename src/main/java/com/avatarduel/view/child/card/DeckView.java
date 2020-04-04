package com.avatarduel.view.child.card;

import com.avatarduel.model.Player;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;
import com.avatarduel.view.RefreshableView;

public class DeckView extends GridView implements RefreshableView {

    private Player player;
    private DefaultText deckValueText;

    public DeckView(Player player) {
        super("100", "100");
        this.player = player;
        this.deckValueText = new DefaultText();

        refreshView();
    }

    @Override
    public void initGUI() {
        add(deckValueText, 0, 0);
    }

    @Override
    public void refreshView() {
        deckValueText.setText(String.format("%d / %d", player.getCurrentDeckCount(), player.getTotalDeckCount()));
    }

}
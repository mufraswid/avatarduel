package com.avatarduel.view.child;

import com.avatarduel.model.Player;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;
import com.avatarduel.view.ViewPosition;

public class StatusView extends GridView {

    private DefaultText nameText, hpText;

    public StatusView(ViewPosition position) {
        super("100", "50,50");
        nameText = new DefaultText();
        hpText = new DefaultText();
        addBorder();

        boolean isTop = position == ViewPosition.TOP;
        add(nameText, 0, isTop ? 0 : 1);
        add(hpText, 0, isTop ? 1 : 0);
    }

    public void render(Player player) {
        nameText.setText(player.getName());
        hpText.setText(player.getHP() + " HP");
    }

}
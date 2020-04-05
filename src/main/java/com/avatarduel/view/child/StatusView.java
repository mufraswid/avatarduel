package com.avatarduel.view.child;

import com.avatarduel.model.Player;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;
import com.avatarduel.view.ViewPosition;

public class StatusView extends GridView {

    public StatusView(Player player) {
        super("100", "50,50");
        DefaultText nameText = new DefaultText(player.getName());
        DefaultText hpText = new DefaultText(player.getHP() + " HP");
        addBorder();

        boolean isTop = player.getPosition() == ViewPosition.TOP;
        add(nameText, 0, isTop ? 0 : 1);
        add(hpText, 0, isTop ? 1 : 0);
    }

}
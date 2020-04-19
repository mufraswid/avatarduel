package com.avatarduel.view.child;

import com.avatarduel.model.IPlayer;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;
import com.avatarduel.view.ViewPosition;

/**
 * Display each player's status
 */
public class StatusView extends GridView {

    private DefaultText nameText, hpText;

    /**
     * Constructor
     *
     * @param position position top or bottom
     */
    public StatusView(ViewPosition position) {
        super("100", "50,50");
        nameText = new DefaultText();
        hpText = new DefaultText();
        addBorder();

        boolean isTop = position == ViewPosition.TOP;
        add(nameText, 0, isTop ? 0 : 1);
        add(hpText, 0, isTop ? 1 : 0);
    }

    /**
     * @param player specified player
     */
    public void render(IPlayer player) {
        nameText.setText(player.getName());
        hpText.setText(player.getHP() + " HP");
    }

}

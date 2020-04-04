package com.avatarduel.view.child;

import com.avatarduel.model.Player;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;
import com.avatarduel.view.RefreshableView;
import com.avatarduel.view.ViewPosition;

public class StatusView extends GridView implements RefreshableView {

    private DefaultText nameText, hpText;
    private Player player;

    public StatusView(Player player) {
        // TODO: Should be player as parameter that has position instead of ViewPosition, so it can name the nameText and hpText
        super("100", "50,50");
        this.player = player;
        this.nameText = new DefaultText();
        this.hpText = new DefaultText();
    }

    @Override
    public void initGUI() {
        boolean isTop = player.getPosition() == ViewPosition.TOP;
        add(nameText, 0, isTop ? 0 : 1);
        add(hpText, 0, isTop ? 1 : 0);
    }

    @Override
    public void refreshView() {
        // TODO Auto-generated method stub

    }

}
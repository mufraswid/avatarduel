package com.avatarduel.view.child;

import com.avatarduel.controller.PlayerController;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;
import com.avatarduel.view.ViewPosition;

public class StatusView extends GridView implements PlayerRenderer {

    public DefaultText nameText, hpText;

    public StatusView() {
        super("100", "50,50");
        this.nameText = new DefaultText();
        this.hpText = new DefaultText();
        initGUI();
    }

    @Override
    public void initGUI() {
        addBorder();
    }

    @Override
    public void renderPlayer(PlayerController player) {
        getChildren().clear();
        boolean isTop = player.getPosition() == ViewPosition.TOP;
        add(nameText, 0, isTop ? 0 : 1);
        add(hpText, 0, isTop ? 1 : 0);
        nameText.setText(player.getName());
        hpText.setText(player.getHP() + " HP");
    }

}
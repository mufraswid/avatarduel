package com.avatarduel.view.main;

import com.avatarduel.controller.GameController;
import com.avatarduel.view.GridView;
import com.avatarduel.view.child.ElementView;
import com.avatarduel.view.child.PhaseView;

public class RightMainView extends GridView implements GameRenderer {

    private PhaseView phaseView;
    private ElementView elementView1, elementView2;

    public RightMainView() {
        super("100", "15,20,30,20,15");
        phaseView = new PhaseView();
        elementView1 = new ElementView();
        elementView2 = new ElementView();
        initGUI();
    }

    @Override
    public void initGUI() {
        add(elementView2, 0, 1);
        add(phaseView, 0, 2);
        add(elementView1, 0, 3);
    }

    @Override
    public void renderGame(GameController game) {
        phaseView.renderGame(game);
        elementView1.renderPlayer(game.getPlayer1());
        elementView2.renderPlayer(game.getPlayer2());
    }

}
package com.avatarduel.view.main;

import com.avatarduel.controller.listener.MouseEventListener;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;
import com.avatarduel.view.GridView;
import com.avatarduel.view.child.ElementView;
import com.avatarduel.view.child.PhaseView;

public class RightMainView extends GridView {

    private ElementView elementView1, elementView2;
    private PhaseView phaseView;

    public RightMainView(MouseEventListener listener) {
        super("100", "15,20,30,20,15");
        add(elementView2 = new ElementView(), 0, 1);
        add(phaseView = new PhaseView(listener), 0, 2);
        add(elementView1 = new ElementView(), 0, 3);
    }

    public void renderElementView2(Player player) {
        elementView2.render(player);
    }

    public void renderPhase(Phase phase) {
        phaseView.render(phase);
    }

    public void renderElementView1(Player player) {
        elementView1.render(player);
    }

}
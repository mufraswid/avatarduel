package com.avatarduel.view.main;

import com.avatarduel.controller.listener.MouseEventListener;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;
import com.avatarduel.view.GridView;
import com.avatarduel.view.child.ElementView;
import com.avatarduel.view.child.PhaseView;

public class RightMainView extends GridView {

    public RightMainView(Phase phase, Player player1, Player player2, MouseEventListener listener) {
        super("100", "15,20,30,20,15");
        add(new ElementView(player2), 0, 1);
        add(new PhaseView(phase, listener), 0, 2);
        add(new ElementView(player1), 0, 3);
    }

}
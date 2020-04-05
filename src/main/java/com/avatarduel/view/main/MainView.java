package com.avatarduel.view.main;

import com.avatarduel.Constants;
import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.controller.listener.MouseEventListener;
import com.avatarduel.model.Phase;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import com.avatarduel.view.GridView;

import javafx.geometry.Insets;

public class MainView extends GridView {

    public MainView(Phase phase, Player player1, Player player2, Card lastTouchedCard,
            CardEventListener handCardListener, CardEventListener cardFieldListener, MouseEventListener phaseListener) {
        super("20,70,10", "100");
        setPadding(new Insets(Constants.GAP, Constants.GAP, Constants.GAP, Constants.GAP));
        add(new LeftMainView(player1, player2, lastTouchedCard), 0, 0);
        add(new CenterMainView(player1, player2, handCardListener, cardFieldListener), 1, 0);
        add(new RightMainView(phase, player1, player2, phaseListener), 2, 0);
    }

}
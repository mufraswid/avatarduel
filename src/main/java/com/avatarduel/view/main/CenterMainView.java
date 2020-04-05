package com.avatarduel.view.main;

import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.Player;
import com.avatarduel.view.GridView;
import com.avatarduel.view.ViewPosition;
import com.avatarduel.view.child.card.CardFieldView;
import com.avatarduel.view.child.card.HandCardFieldView;

public class CenterMainView extends GridView {

    public CenterMainView(Player player1, Player player2, CardEventListener handCardListener,
            CardEventListener cardFieldListener) {
        super("100", "18,32,32,18");
        add(new HandCardFieldView(player2, handCardListener), 0, 0);
        add(new CardFieldView(player2, ViewPosition.TOP, cardFieldListener), 0, 1);
        add(new CardFieldView(player1, ViewPosition.BOTTOM, cardFieldListener), 0, 2);
        add(new HandCardFieldView(player1, handCardListener), 0, 3);
    }

}
package com.avatarduel.view.main;

import com.avatarduel.controller.Game;
import com.avatarduel.view.GridView;
import com.avatarduel.view.child.card.CardFieldView;
import com.avatarduel.view.child.card.HandCardFieldView;

public class CenterMainView extends GridView {

    private HandCardFieldView handCardView1, handCardView2;
    private CardFieldView cardFieldView1, cardFieldView2;

    public CenterMainView() {
        super("100", "15,35,35,15");
        Game game = Game.getInstance();
        handCardView1 = new HandCardFieldView(game.getPlayer1());
        handCardView2 = new HandCardFieldView(game.getPlayer2());
        cardFieldView1 = new CardFieldView(game.getPlayer1());
        cardFieldView2 = new CardFieldView(game.getPlayer2());
    }

    @Override
    public void initGUI() {
        add(handCardView2, 0, 0);
        add(cardFieldView2, 0, 1);
        add(cardFieldView1, 0, 2);
        add(handCardView1, 0, 3);
    }

}
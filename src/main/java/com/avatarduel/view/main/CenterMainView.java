package com.avatarduel.view.main;

import com.avatarduel.view.GridView;
import com.avatarduel.view.ViewPosition;
import com.avatarduel.view.child.card.CardFieldView;
import com.avatarduel.view.child.card.HandCardFieldView;

public class CenterMainView extends GridView {

    public HandCardFieldView handCardView1, handCardView2;
    public CardFieldView cardFieldView1, cardFieldView2;

    public CenterMainView() {
        super("100", "15,35,35,15");
        handCardView1 = new HandCardFieldView();
        handCardView2 = new HandCardFieldView();
        cardFieldView1 = new CardFieldView(ViewPosition.BOTTOM);
        cardFieldView2 = new CardFieldView(ViewPosition.TOP);
        initGUI();
    }

    @Override
    public void initGUI() {
        add(handCardView2, 0, 0);
        add(cardFieldView2, 0, 1);
        add(cardFieldView1, 0, 2);
        add(handCardView1, 0, 3);
    }

}
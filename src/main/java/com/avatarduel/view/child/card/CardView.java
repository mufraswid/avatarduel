package com.avatarduel.view.child.card;

import com.avatarduel.view.GridView;

public abstract class CardView extends GridView {

    public CardView(String colPercentages, String rowPercentages) {
        super(colPercentages, rowPercentages);
        addBorder();
    }

}
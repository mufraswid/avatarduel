package com.avatarduel.view.child.card;

import com.avatarduel.view.GridView;

/**
 * Abstract class to display a card
 */
public abstract class CardView extends GridView {

    /**
     * Constructor
     *
     * @param colPercentages column percentages
     * @param rowPercentages row percentages
     */
    public CardView(String colPercentages, String rowPercentages) {
        super(colPercentages, rowPercentages);
        addBorder();
    }

}

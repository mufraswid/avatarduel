package com.avatarduel.view.child.card;

import com.avatarduel.model.CardPosition;
import com.avatarduel.model.card.Card;

public class SmallCardView extends CardView {

    private boolean closed;

    public SmallCardView(Card card) {
        super(card, colPercentages, rowPercentages);
        setClosed(true);
    }

    @Override
    public void initGUI() {
        // TODO: Implement

    }

    @Override
    public void refreshView() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

}
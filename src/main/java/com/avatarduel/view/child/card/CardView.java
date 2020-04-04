package com.avatarduel.view.child.card;

import com.avatarduel.model.card.Card;
import com.avatarduel.view.GridView;
import com.avatarduel.view.RefreshableView;

public abstract class CardView extends GridView implements RefreshableView, ClosableCard {

    protected Card card;
    protected boolean closed;

    public CardView(Card card, String colPercentages, String rowPercentages) {
        super(colPercentages, rowPercentages);
        this.card = card;
        this.closed = true;
        addBorder();
    }

    public void setCard(Card card) {
        if ((this.card = card) != null) {
            refreshView();
        }
    }

    public boolean hasCard() {
        return this.card != null;
    }

    public Card getCard() {
        return this.card;
    }

    @Override
    public boolean isClosed() {
        return this.closed;
    }

    @Override
    public void setClosed(boolean closed) {
        this.closed = closed;
        refreshView();
    }

}
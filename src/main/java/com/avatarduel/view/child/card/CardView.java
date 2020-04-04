package com.avatarduel.view.child.card;

import com.avatarduel.model.card.Card;
import com.avatarduel.view.GridView;
import com.avatarduel.view.RefreshableView;

public abstract class CardView extends GridView implements RefreshableView, ClosableCard {

    protected Card card;
    protected boolean closed;

    public CardView(Card card, String colPercentages, String rowPercentages) {
        super(colPercentages, rowPercentages);
        setCard(card);
        setClosed(true);
    }

    public void setCard(Card card) {
        this.card = card;
        refreshView();
    }

    public Card getCard() {
        return this.card;
    }

    @Override
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

}
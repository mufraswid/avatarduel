package com.avatarduel.controller.card;

import com.avatarduel.model.card.Card;

public class CardController {

    private Card card;

    public CardController(Card card) {
        setCard(card);
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CardController)) {
            return false;
        }
        CardController card = (CardController) o;
        return getCard().equals(card.getCard());
    }

}
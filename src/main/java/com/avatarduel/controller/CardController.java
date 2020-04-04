package com.avatarduel.controller;

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
}
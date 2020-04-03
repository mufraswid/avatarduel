package com.avatarduel.view;

import com.avatarduel.model.card.Card;

import javafx.scene.layout.GridPane;

public abstract class CardView extends GridPane implements View {

    protected Card card;

    public void setCard(Card card) {
        this.card = card;
        render();
    }

    public Card getCard() {
        return this.card;
    }

}
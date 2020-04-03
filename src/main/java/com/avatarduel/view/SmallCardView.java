package com.avatarduel.view;

import com.avatarduel.model.Position;
import com.avatarduel.model.card.Card;

public class SmallCardView extends CardView {

    private Position position;

    private void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setCard(Card card, Position position) {
        setPosition(position);
        super.setCard(card);
    }

    @Override
    public void setCard(Card card) {
        setCard(card, Position.ATTACK);
    }

    @Override
    public void render() {
        // TODO: Implement

    }

}
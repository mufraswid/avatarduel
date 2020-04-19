package com.avatarduel.model.card;

/**
 * Represent a closed card
 */
public class ClosedCard extends Card {

    /**
     * Constructor for a closed card
     */
    public ClosedCard() {
        super("card/image/closed.png", "", "", null);
    }

    /**
     * @return a new copy of this card
     */
    @Override
    public Card copy() {
        return this;
    }

}

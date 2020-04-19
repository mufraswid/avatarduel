package com.avatarduel.model.card;

import com.avatarduel.model.Element;

/**
 * Define the contract for a powered card
 */
public interface PoweredCard {
    /**
     * @return the needed power
     */
    public int getPowerNeeded();

    /**
     * @return element type
     */
    public Element getElementType();
}

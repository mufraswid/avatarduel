package com.avatarduel.model.card;

import com.avatarduel.model.Element;

/**
 * Represents a land card
 */
public class LandCard extends Card {
    /**
     * Constructor for land card
     *
     * @param imagePath   path to image resource
     * @param name        name for the card
     * @param description description for the card
     * @param elementType element type of land card
     */
    public LandCard(String imagePath, String name, String description, Element elementType) {
        super(imagePath, name, description, elementType);
    }

    /**
     * @return this card description
     */
    @Override
    public String getDescription() {
        return "Land Card\n" + super.getDescription();
    }

    /**
     * @return a new copy of this card
     */
    @Override
    public Card copy() {
        return new LandCard(imagePath, name, description, elementType);
    }
}

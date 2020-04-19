package com.avatarduel.model.card;

import com.avatarduel.model.Element;
import com.avatarduel.model.IPlayer;

/**
 * Represents a land card
 */
public class LandCard extends ActivableCard {
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

    @Override
    public boolean canBePutOn(IPlayer player) {
        return super.canBePutOn(player) && !player.hasPutLandCard();
    }

    @Override
    public void putOn(IPlayer player) {
        super.putOn(player);
        player.addMaxElement(getElementType());
        player.setHasPutLandCard(true);
    }
}

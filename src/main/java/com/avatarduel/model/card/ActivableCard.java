package com.avatarduel.model.card;

import com.avatarduel.model.Element;
import com.avatarduel.model.IPlayer;

public abstract class ActivableCard extends Card {

    public ActivableCard(String imagePath, String name, String description, Element elementType) {
        super(imagePath, name, description, elementType);
    }

    /** 
     * @param player
     * @return boolean
     */
    public boolean canBePutOn(IPlayer player) {
        return player.hasOnHand(this);
    }

    /** 
     * @param player
     */
    public void putOn(IPlayer player) {
        player.removeFromHand(this);
    }

    /**
     * @return this card type name
     */
    public abstract String getCardTypeName();

    /**
     * @return this card's full description on view
     */
    @Override
    public String getFullDescription() {
        return getCardTypeName() + '\n' + getDescription();
    }

}
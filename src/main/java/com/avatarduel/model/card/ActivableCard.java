package com.avatarduel.model.card;

import com.avatarduel.model.Element;
import com.avatarduel.model.IPlayer;

public abstract class ActivableCard extends Card {

    public ActivableCard(String imagePath, String name, String description, Element elementType) {
        super(imagePath, name, description, elementType);
    }

    public boolean canBePutOn(IPlayer player) {
        return player.hasOnHand(this);
    }

    public void putOn(IPlayer player) {
        player.removeFromHand(this);
    }

}
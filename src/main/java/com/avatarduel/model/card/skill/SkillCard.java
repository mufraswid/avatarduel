package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;
import com.avatarduel.model.IPlayer;
import com.avatarduel.model.card.ActivableCard;
import com.avatarduel.model.card.PoweredCard;

/**
 * Abstract class to represents a skill card
 */
public abstract class SkillCard extends ActivableCard implements PoweredCard {

    protected int powerNeeded;

    /**
     * Constructor for skill card
     *
     * @param imagePath   path to image resource
     * @param name        name for the card
     * @param description description for the card
     * @param elementType element type of this card
     * @param powerNeeded amount of power needed
     */
    public SkillCard(String imagePath, String name, String description, Element elementType, int powerNeeded) {
        super(imagePath, name, description, elementType);
        this.powerNeeded = powerNeeded;
    }

    /**
     * @return the power needed
     */
    @Override
    public int getPowerNeeded() {
        return this.powerNeeded;
    }

    @Override
    public boolean canBePutOn(IPlayer player) {
        return super.canBePutOn(player) && player.canSpendPower(this);
    }

    @Override
    public void putOn(IPlayer player) {
        super.putOn(player);
        player.spendPower(this);
    }
}

package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.PoweredCard;

/**
 * Abstract class to represents a skill card
 */
public abstract class SkillCard extends Card implements PoweredCard {
    // SkillCard attribute
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

    // #region getter
    /**
     * @return the power needed
     */
    @Override
    public int getPowerNeeded() {
        return this.powerNeeded;
    }
    // #endregion
}

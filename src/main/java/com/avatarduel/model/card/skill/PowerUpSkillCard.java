package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.Card;

/**
 * Represents power up skill card
 */
public class PowerUpSkillCard extends PutableSkillCard {

    /**
     * Constructor
     *
     * @param imagePath   path to image resource
     * @param name        name for the card
     * @param description description for the card
     * @param elementType element type of this card
     * @param powerNeeded amount of power needed
     */
    public PowerUpSkillCard(String imagePath, String name, String description, Element elementType, int powerNeeded) {
        super(imagePath, name, description, elementType, powerNeeded);
    }

    /**
     * @return this card description
     */
    @Override
    public String getDescription() {
        return "PowerUp Skill Card\n" + super.getDescription();
    }

    /**
     * @return this card
     */
    @Override
    public PowerUpSkillCard createArenaCard() {
        return this;
    }

    /**
     * @return a new copy of this card
     */
    @Override
    public Card copy() {
        return new PowerUpSkillCard(imagePath, name, description, elementType, powerNeeded);
    }

}

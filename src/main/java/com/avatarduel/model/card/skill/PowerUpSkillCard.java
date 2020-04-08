package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.ActivableCard;
import com.avatarduel.model.card.Card;

/**
 * Kelas KartuSkillPowerUp: kelas yang memodelkan konsep kartu skill tipe power up, diturunkan dari kelas KartuSkill
 */
public class PowerUpSkillCard extends SkillCard implements ActivableCard {
    // Konstruktor
    public PowerUpSkillCard(String imagePath, String name, String description, Element elementType, int powerNeeded) {
        super(imagePath, name, description, elementType, powerNeeded);
    }

    @Override
    public PowerUpSkillCard createActiveCard() {
        return this;
    }

    @Override
    public Card copy() {
        return new PowerUpSkillCard(imagePath, name, description, elementType, powerNeeded);
    }

}

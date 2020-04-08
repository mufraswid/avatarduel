package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.PutableCard;
import com.avatarduel.model.card.Card;

/**
 * Kelas KartuSkillPowerUp: kelas yang memodelkan konsep kartu skill tipe power up, diturunkan dari kelas KartuSkill
 */
public class PowerUpSkillCard extends SkillCard implements PutableCard {
    // Konstruktor
    public PowerUpSkillCard(String imagePath, String name, String description, Element elementType, int powerNeeded) {
        super(imagePath, name, description, elementType, powerNeeded);
    }

    @Override
    public String getDescription() {
        return "PowerUp Skill Card\n" + super.getDescription();
    }

    @Override
    public PowerUpSkillCard createArenaCard() {
        return this;
    }

    @Override
    public Card copy() {
        return new PowerUpSkillCard(imagePath, name, description, elementType, powerNeeded);
    }

}

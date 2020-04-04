package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;

/**
 * Kelas KartuSkillPowerUp: kelas yang memodelkan konsep kartu skill tipe power up, diturunkan dari kelas KartuSkill
 */
public class PowerUpSkillCard extends SkillCard {
    // Konstruktor
    public PowerUpSkillCard(String imagePath, int id, String name, String description, Element elementType,
            int powerNeeded) {
        super(imagePath, id, name, description, elementType, powerNeeded);
    }

    @Override
    public String getDescription() {
        return "POWERUP SKILL CARD\n" + super.getDescription();
    }

}

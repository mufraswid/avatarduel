package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.CharacterCard;

/**
 * Kelas KartuSkillPowerUp: kelas yang memodelkan konsep kartu skill tipe power up, diturunkan dari kelas KartuSkill
 */
public class PowerUpSkillCard extends SkillCard {
    // Konstruktor
    public PowerUpSkillCard(String imagePath, int id, String name, String description, Element elementType,
            int powerNeeded) {
        super(imagePath, id, name, description, elementType, powerNeeded);
    }

    // override applyEffect() dari KartuSkill: mengurangi HP lawan jika target menyerang kartu lawan seakan kartu lawan sedang menyerang
    @Override
    public void applyEffect(CharacterCard characterCard) {
        // TODO: Implement!
    }

    @Override
    public String getDescription() {
        return "POWERUP SKILL CARD\n" + super.getDescription();
    }

    @Override
    public void revertEffect(CharacterCard characterCard) {
        // TODO Auto-generated method stub

    }
}

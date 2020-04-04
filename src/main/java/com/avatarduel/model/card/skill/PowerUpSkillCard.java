package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.CharacterCard;

/**
 * Kelas KartuSkillPowerUp: kelas yang memodelkan konsep kartu skill tipe power up, diturunkan dari kelas KartuSkill
 */
public class PowerUpSkillCard extends SkillCard {
    // Konstruktor
    public PowerUpSkillCard(String imagePath, String name, String description, Element elementType, int powerNeeded) {
        super(imagePath, name, description, elementType, powerNeeded);
    }

    // override applyEffect() dari KartuSkill: mengurangi HP lawan jika target menyerang kartu lawan seakan kartu lawan sedang menyerang
    @Override
    public void applyEffect(CharacterCard characterCard) {
        // TODO: Implement!
    }
}

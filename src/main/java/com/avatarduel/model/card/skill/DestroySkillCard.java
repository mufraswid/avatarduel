package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.Card;

/**
 * Kelas KartuSkillDestroy: kelas yang memodelkan konsep kartu skill tipe destroy, diturunkan dari kelas KartuSkill
 */
public class DestroySkillCard extends SkillCard {
    // Konstruktor
    public DestroySkillCard(String imagePath, String name, String description, Element elementType, int powerNeeded) {
        super(imagePath, name, description, elementType, powerNeeded);
    }

    @Override
    public String getDescription() {
        return "Destroy Skill Card\n" + super.getDescription();
    }

    @Override
    public Card copy() {
        return new DestroySkillCard(imagePath, name, description, elementType, powerNeeded);
    }

}

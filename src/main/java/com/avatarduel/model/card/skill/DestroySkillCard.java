package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;

/**
 * Kelas KartuSkillDestroy: kelas yang memodelkan konsep kartu skill tipe destroy, diturunkan dari kelas KartuSkill
 */
public class DestroySkillCard extends SkillCard {
    // Konstruktor
    public DestroySkillCard(String imagePath, int id, String name, String description, Element elementType,
            int powerNeeded) {
        super(imagePath, id, name, description, elementType, powerNeeded);
    }

    @Override
    public String getDescription() {
        return "DESTROY SKILL CARD\n" + super.getDescription();
    }

}

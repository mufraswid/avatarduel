package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.Card;

/**
 * Kelas abstrak Kartu Skill: kelas yang memodelkan konsep kartu skill, merupakan subclass dari kelas Kartu, 
 * menurunkan jenis KartuSkillAura, KartuSkillDestroy, dan KartuSkillPowerUp.
 */
public abstract class SkillCard extends Card {
    // Atribut dari KartuSkill
    private int powerNeeded;

    // Konstruktor
    public SkillCard(String imagePath, int id, String name, String description, Element elementType, int powerNeeded) {
        super(imagePath, id, name, description, elementType);
        setPowerNeeded(powerNeeded);
    }

    //#region setter
    private void setPowerNeeded(int powerNeeded) {
        this.powerNeeded = powerNeeded;
    }
    //#endregion

    //#region getter
    public int getPowerNeeded() {
        return this.powerNeeded;
    }
    //#endregion
}

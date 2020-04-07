package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.PoweredCard;

/**
 * Kelas abstrak Kartu Skill: kelas yang memodelkan konsep kartu skill, merupakan subclass dari kelas Kartu, 
 * menurunkan jenis KartuSkillAura, KartuSkillDestroy, dan KartuSkillPowerUp.
 */
public abstract class SkillCard extends Card implements PoweredCard {
    // Atribut dari KartuSkill
    private int powerNeeded;

    // Konstruktor
    public SkillCard(String imagePath, String name, String description, Element elementType, int powerNeeded) {
        super(imagePath, name, description, elementType);
        setPowerNeeded(powerNeeded);
    }

    //#region setter
    private void setPowerNeeded(int powerNeeded) {
        this.powerNeeded = powerNeeded;
    }
    //#endregion

    //#region getter
    @Override
    public int getPowerNeeded() {
        return this.powerNeeded;
    }
    //#endregion
}

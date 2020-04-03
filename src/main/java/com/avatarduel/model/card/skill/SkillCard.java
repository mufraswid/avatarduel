package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;

/**
 * Kelas abstrak Kartu Skill: kelas yang memodelkan konsep kartu skill, merupakan subclass dari kelas Kartu, 
 * menurunkan jenis KartuSkillAura, KartuSkillDestroy, dan KartuSkillPowerUp.
 */
public abstract class SkillCard extends Card {
    // Atribut dari KartuSkill
    private int powerNeeded;

    public SkillCard(String imagePath, String name, String description, Element elementType, int powerNeeded) {
        super(imagePath, name, description, elementType);
        setPowerNeeded(powerNeeded);
    }

    // Fungsi untuk mengaplikasikan efek ke kartu applyEffect() diaplikasikan
    public abstract void applyEffect(CharacterCard characterCard);

    //#region setter
    private void setPowerNeeded(int powerNeeded) {
        this.powerNeeded = powerNeeded;
    }
    //#endregion

    //#region getter
    public int getPowerNeeded() {
        return this.powerNeeded;
    }
}

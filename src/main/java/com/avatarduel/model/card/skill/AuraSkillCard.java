package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.CharacterCard;

/**
 * Kelas KartuSkillAura: kelas yang memodelkan konsep kartu skill tipe Aura, diturunkan dari kelas KartuSkill
 */
public class AuraSkillCard extends SkillCard {
    // Atribut: untuk menambahkan atk dan def dari kartu target. Dapat bernilai 0 dan negatif
    private int attackAddition, defenseAddition;

    // Konstruktor
    public AuraSkillCard(String imagePath, int id, String name, String description, Element elementType, int powerNeeded,
            int attackAddition, int defenseAddition) {
        super(imagePath, id, name, description, elementType, powerNeeded);
        setAttackAddition(attackAddition);
        setDefenseAddition(defenseAddition);
    }

    // override applyEffect() dari KartuSkill: menambah atk dan defense target saat menyerang/diserang;
    @Override
    public void applyEffect(CharacterCard characterCard) {
        characterCard.setDeltaAttack(this.attackAddition);
        characterCard.setDeltaDefense(this.defenseAddition);
    }

    @Override
    public void revertEffect(CharacterCard characterCard) {
        characterCard.setDeltaAttack(-this.attackAddition);
        characterCard.setDeltaDefense(-this.defenseAddition);
    }

    //#region setter
    private void setAttackAddition(int attackAddition) {
        this.attackAddition = attackAddition;
    }

    private void setDefenseAddition(int defenseAddition) {
        this.defenseAddition = defenseAddition;
    }
    //#endregion

    //#region getter
    public int getAttackAddition() {
        return this.attackAddition;
    }

    public int getDefenseAddition() {
        return this.defenseAddition;
    }

    @Override
    public String getDescription() {
        return "AURA SKILL CARD\n" + super.getDescription();
    }
    //#endregion
}

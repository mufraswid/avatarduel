package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.ActivableCard;
import com.avatarduel.model.card.Card;

/**
 * Kelas KartuSkillAura: kelas yang memodelkan konsep kartu skill tipe Aura, diturunkan dari kelas KartuSkill
 */
public class AuraSkillCard extends SkillCard implements ActivableCard {
    // Atribut: untuk menambahkan atk dan def dari kartu target. Dapat bernilai 0 dan negatif
    private int attackAddition, defenseAddition;

    // Konstruktor
    public AuraSkillCard(String imagePath, String name, String description, Element elementType, int powerNeeded,
            int attackAddition, int defenseAddition) {
        super(imagePath, name, description, elementType, powerNeeded);
        setAttackAddition(attackAddition);
        setDefenseAddition(defenseAddition);
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
    //#endregion

    @Override
    public AuraSkillCard createActiveCard() {
        return this;
    }

    @Override
    public Card copy() {
        return new AuraSkillCard(imagePath, name, description, elementType, powerNeeded, attackAddition,
                defenseAddition);
    }
}

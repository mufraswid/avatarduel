package com.avatarduel.model.card;

import java.util.ArrayList;
import java.util.List;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.skill.SkillCard;

/**
 * Kelas CharacterCard: kelas yang memodelkan konsep kartu karakter, merupakan subclass dari kelas Kartu
 */
public class CharacterCard extends Card {

    /** 
    * Atribut dari CharacterCard: atk untuk attack, def untuk defense dari kartu, powerNeeded untuk jumlah power yang dibutuhkan, 
    * skillcard untuk kartu skill yang terkait oleh kartu ini, deltaAttack dan deltaDefense untuk penambahan atk dan def dari skillcard
    */
    private int powerNeeded, attack, defense, deltaAttack, deltaDefense;
    private List<SkillCard> skillList;

    // constructor
    public CharacterCard(String imagePath, int id, String name, String description, Element elementType,
            int powerNeeded, int attack, int defense) {
        super(imagePath, id, name, description, elementType);
        setPowerNeeded(powerNeeded);
        setAttack(attack);
        setDefense(defense);
        skillList = new ArrayList<>();
        this.deltaAttack = 0;
        this.deltaDefense = 0;
    }

    //#region setter
    private void setAttack(int attack) {
        this.attack = attack;
    }

    private void setDefense(int defense) {
        this.defense = defense;
    }

    private void setPowerNeeded(int power) {
        this.powerNeeded = power;
    }

    public void setDeltaAttack(int delta) {
        this.deltaAttack += delta;
    }

    public void setDeltaDefense(int delta) {
        this.deltaDefense += delta;
    }
    //#endregion

    //#region getter
    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getPowerNeeded() {
        return this.powerNeeded;
    }

    @Override
    public String getDescription() {
        return "CHARACTER CARD\n" + super.getDescription();
    }

    public int getDeltaAttack() {
        return this.deltaAttack;
    }

    public int getDeltaDefense() {
        return this.deltaDefense;
    }
    //#endregion

    //#skill list operations
    public void addSkill(SkillCard skillcard) {
        skillcard.applyEffect(this);
        this.skillList.add(skillcard);
    }

    public void removeSkill(SkillCard skillcard) {
        // TODO! IMPLEMENT
    }
}

package com.avatarduel.model.card;

import com.avatarduel.model.Element;

/**
 * Kelas CharacterCard: kelas yang memodelkan konsep kartu karakter, merupakan subclass dari kelas Kartu
 */
public class CharacterCard extends Card implements PoweredCard, ActivableCard {

    /** 
    * Atribut dari CharacterCard: atk untuk attack, def untuk defense dari kartu, powerNeeded untuk jumlah power yang dibutuhkan, 
    * skillcard untuk kartu skill yang terkait oleh kartu ini, deltaAttack dan deltaDefense untuk penambahan atk dan def dari skillcard
    */
    private int powerNeeded, attack, defense;

    // constructor
    public CharacterCard(String imagePath, String name, String description, Element elementType, int powerNeeded,
            int attack, int defense) {
        super(imagePath, name, description, elementType);
        setPowerNeeded(powerNeeded);
        setAttack(attack);
        setDefense(defense);
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

    @Override
    public ActiveCharacterCard createActiveCard() {
        return new ActiveCharacterCard(this);
    }
    //#endregion

}

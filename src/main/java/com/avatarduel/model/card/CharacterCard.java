package com.avatarduel.model.card;

import com.avatarduel.model.Element;

/**
 * Kelas Kartu Karakter: kelas yang memodelkan konsep kartu karakter, merupakan subclass dari kelas Kartu
 */
public class CharacterCard extends Card {

    // Atribut dari KartuKarakter: atk untuk attack dan def untuk defense dari kartu
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
    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setPowerNeeded(int power) {
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
    //#endregion
}

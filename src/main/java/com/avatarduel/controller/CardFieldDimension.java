package com.avatarduel.controller;

public class CardFieldDimension {
    private int characterCardCount, skillCardCount;

    public CardFieldDimension(int characterCardCount, int skillCardCount) {
        this.characterCardCount = characterCardCount;
        this.skillCardCount = skillCardCount;
    }

    public int getCharacterCardCount() {
        return this.characterCardCount;
    }

    public int getSkillCardCount() {
        return this.skillCardCount;
    }

}
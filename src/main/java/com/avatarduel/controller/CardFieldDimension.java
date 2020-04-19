package com.avatarduel.controller;

public class CardFieldDimension {
    private int characterCardCount, skillCardCount;

    public CardFieldDimension(int characterCardCount, int skillCardCount) {
        this.characterCardCount = characterCardCount;
        this.skillCardCount = skillCardCount;
    }

    /**
     * Character count on field
     * @return int
     */
    public int getCharacterCardCount() {
        return this.characterCardCount;
    }

    /**
     * Skill count on field
     * @return int
     */
    public int getSkillCardCount() {
        return this.skillCardCount;
    }

}
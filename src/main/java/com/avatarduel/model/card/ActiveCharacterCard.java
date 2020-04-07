package com.avatarduel.model.card;

import java.util.ArrayList;
import java.util.List;

import com.avatarduel.model.CardPosition;
import com.avatarduel.model.card.skill.AuraSkillCard;
import com.avatarduel.model.card.skill.SkillCard;

public class ActiveCharacterCard extends CharacterCard {

    private List<SkillCard> skillCardList;
    private CardPosition position;

    private boolean hasAttacked, isEnableToAttack;

    public ActiveCharacterCard(CharacterCard card) {
        super(card.getImagePath(), card.getName(), card.getDescription(), card.getElementType(), card.getPowerNeeded(),
                card.getAttack(), card.getDefense());
        setPosition(CardPosition.ATTACK);
        skillCardList = new ArrayList<>();
        isEnableToAttack = false;
        hasAttacked = false;
    }

    public boolean isEnableToAttack() {
        return isEnableToAttack;
    }

    public void setIsEnableToAttack(boolean b) {
        isEnableToAttack = b;
    }

    public boolean hasAttacked() {
        return hasAttacked;
    }

    public void setHasAttacked(boolean b) {
        hasAttacked = b;
    }

    public CardPosition getPosition() {
        return position;
    }

    public void setPosition(CardPosition position) {
        this.position = position;
    }

    public void switchPosition() {
        setPosition(CardPosition.values()[(position.ordinal() + 1) % CardPosition.values().length]);
    }

    public void addSkill(SkillCard skillCard) {
        skillCardList.add(skillCard);
    }

    public void removeSkill(SkillCard skillCard) {
        skillCardList.remove(skillCard);
    }

    public int getDeltaAttack() {
        int delta = 0;
        for (SkillCard card : skillCardList) {
            if (card instanceof AuraSkillCard) {
                delta += ((AuraSkillCard) card).getAttackAddition();
            }
        }
        return delta;
    }

    public int getDeltaDefense() {
        int delta = 0;
        for (SkillCard card : skillCardList) {
            if (card instanceof AuraSkillCard) {
                delta += ((AuraSkillCard) card).getDefenseAddition();
            }
        }
        return delta;
    }

    public int getTotalAttack() {
        return getAttack() + getDeltaAttack();
    }

    public int getTotalDefense() {
        return getDefense() + getDeltaDefense();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    public List<SkillCard> getSkillCardList() {
        return skillCardList;
    }

}
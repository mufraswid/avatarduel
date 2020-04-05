package com.avatarduel.model.card;

import java.util.ArrayList;
import java.util.List;

import com.avatarduel.model.CardPosition;
import com.avatarduel.model.card.ActiveCharacterCard;
import com.avatarduel.model.card.skill.AuraSkillCard;

public class ActiveCharacterCard extends CharacterCard {

    private List<AuraSkillCard> auraSkillList;
    private CardPosition position;

    public ActiveCharacterCard(CharacterCard card) {
        super(card.getImagePath(), card.getId(), card.getName(), card.getDescription(), card.getElementType(),
                card.getPowerNeeded(), card.getAttack(), card.getDefense());
        setPosition(CardPosition.ATTACK);
        auraSkillList = new ArrayList<>();
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

    public void addSkill(AuraSkillCard auraSkillcard) {
        auraSkillList.add(auraSkillcard);
    }

    public void removeSkill(AuraSkillCard auraSkillcard) {
        auraSkillList.remove(auraSkillcard);
    }

    public int getDeltaAttack() {
        int delta = 0;
        for (AuraSkillCard card : auraSkillList) {
            delta += card.getAttackAddition();
        }
        return delta;
    }

    public int getDeltaDefense() {
        int delta = 0;
        for (AuraSkillCard card : auraSkillList) {
            delta += card.getDefenseAddition();
        }
        return delta;
    }

    public int getTotalAttack() {
        return getAttack() + getDeltaAttack();
    }

    public int getTotalDefense() {
        return getDefense() + getDeltaDefense();
    }

}
package com.avatarduel.controller.card;

import java.util.ArrayList;
import java.util.List;

import com.avatarduel.model.CardPosition;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.skill.AuraSkillCard;

public class CharacterCardController extends CardController {

    private List<AuraSkillCard> auraSkillList;
    private CardPosition position;

    public CharacterCardController(CharacterCard card) {
        super(card);
        setPosition(CardPosition.ATTACK);
        auraSkillList = new ArrayList<>();
    }

    public CardPosition getPosition() {
        return position;
    }

    public CharacterCard getCharacterCard() {
        return (CharacterCard) getCard();
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
        return getCharacterCard().getAttack() + getDeltaAttack();
    }

    public int getTotalDefense() {
        return getCharacterCard().getDefense() + getDeltaDefense();
    }

}
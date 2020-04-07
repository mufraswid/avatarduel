package com.avatarduel.model.card;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.avatarduel.model.CardPosition;
import com.avatarduel.model.card.skill.AuraSkillCard;
import com.avatarduel.model.card.skill.SkillCard;

public class ActiveCharacterCard extends CharacterCard {

    private List<SkillCard> skillCardList;
    private CardPosition position;
    private UUID uuid;

    private boolean hasAttacked, isEnableToAttack;

    public ActiveCharacterCard(CharacterCard card) {
        super(card.getImagePath(), card.getName(), card.getDescription(), card.getElementType(), card.getPowerNeeded(),
                card.getAttack(), card.getDefense());
        setPosition(CardPosition.ATTACK);
        skillCardList = new ArrayList<>();
        uuid = UUID.randomUUID();
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ActiveCharacterCard other = (ActiveCharacterCard) obj;
        if (uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!uuid.equals(other.uuid))
            return false;
        return true;
    }

}
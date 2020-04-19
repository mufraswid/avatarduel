package com.avatarduel.model.card;

import java.util.ArrayList;
import java.util.List;

import com.avatarduel.model.CardPosition;
import com.avatarduel.model.card.skill.AuraSkillCard;
import com.avatarduel.model.card.skill.PowerUpSkillCard;
import com.avatarduel.model.card.skill.SkillCard;

/**
 * Represents a card in arena
 */
public class ArenaCharacterCard extends CharacterCard {

    private List<SkillCard> skillCardList;
    private CardPosition position;

    private boolean hasAttacked, isEnableToAttack;

    /**
     * Constructor for arena character card from a specified character card
     *
     * @param card specified character card
     */
    public ArenaCharacterCard(CharacterCard card) {
        super(card.getImagePath(), card.getName(), card.getDescription(), card.getElementType(), card.getPowerNeeded(),
                card.getAttack(), card.getDefense());
        setPosition(CardPosition.ATTACK);
        skillCardList = new ArrayList<>();
        isEnableToAttack = false;
        hasAttacked = false;
    }

    /**
     * @return true if this card can atack, false otherwise
     */
    public boolean isEnableToAttack() {
        return isEnableToAttack;
    }

    /**
     * @param b set this card ability to attack
     */
    public void setIsEnableToAttack(boolean b) {
        isEnableToAttack = b;
    }

    /**
     * @return true if this card already attack, false otherwise
     */
    public boolean hasAttacked() {
        return hasAttacked;
    }

    /**
     * @param b mark whether this card already attacked
     */
    public void setHasAttacked(boolean b) {
        hasAttacked = b;
    }

    /**
     * @return position of this card
     */
    public CardPosition getPosition() {
        return position;
    }

    /**
     * @param position position to be set
     */
    public void setPosition(CardPosition position) {
        this.position = position;
    }

    /**
     * Switch this card position
     */
    public void switchPosition() {
        setPosition(CardPosition.values()[(position.ordinal() + 1) % CardPosition.values().length]);
    }

    /**
     * Add specified skill card
     *
     * @param skillCard specified skill card
     */
    public void addSkill(SkillCard skillCard) {
        skillCardList.add(skillCard);
    }

    /**
     * Remove specified skill card
     *
     * @param skillCard specified skill card
     */
    public void removeSkill(SkillCard skillCard) {
        skillCardList.remove(skillCard);
    }

    /**
     * @return delta attack
     */
    public int getDeltaAttack() {
        int delta = 0;
        for (SkillCard card : skillCardList) {
            if (card instanceof AuraSkillCard) {
                delta += ((AuraSkillCard) card).getAttackAddition();
            }
        }
        return delta;
    }

    /**
     * @return delta defense
     */
    public int getDeltaDefense() {
        int delta = 0;
        for (SkillCard card : skillCardList) {
            if (card instanceof AuraSkillCard) {
                delta += ((AuraSkillCard) card).getDefenseAddition();
            }
        }
        return delta;
    }

    /**
     * @return total attack
     */
    public int getTotalAttack() {
        return getAttack() + getDeltaAttack();
    }

    /**
     * @return total defense
     */
    public int getTotalDefense() {
        return getDefense() + getDeltaDefense();
    }

    /**
     * @return list of skill card
     */
    public List<SkillCard> getSkillCardList() {
        return skillCardList;
    }

    /**
     * @return true if this card is powered up, false otherwise
     */
    public boolean isPoweredUp() {
        return skillCardList.stream().anyMatch(c -> c instanceof PowerUpSkillCard);
    }

}

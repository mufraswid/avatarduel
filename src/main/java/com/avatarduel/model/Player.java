package com.avatarduel.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.avatarduel.controller.CardFieldDimension;
import com.avatarduel.model.card.ActivableCard;
import com.avatarduel.model.card.ArenaCharacterCard;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.PoweredCard;
import com.avatarduel.model.card.skill.PutableSkillCard;

/**
 * Represents a player
 */
public class Player implements IPlayer {

    private int[] currentElementValues, maxElementValues;
    private int totalDeckCount, hp;
    private List<ActivableCard> deck, handCards;
    private List<ArenaCharacterCard> characterFieldCards;
    private List<PutableSkillCard> skillFieldCards;
    private String name;
    private boolean hasPutLandCard;
    private CardFieldDimension cardFieldDimension;

    /**
     * Constructor for player with a specified name
     *
     * @param name the name of the player
     */
    public Player(String name, CardFieldDimension cardFieldDimension) {
        this.name = name;
        int elementLength = Element.values().length;
        currentElementValues = new int[elementLength];
        maxElementValues = new int[elementLength];
        totalDeckCount = 0;
        deck = new ArrayList<>();
        handCards = new ArrayList<>();
        this.cardFieldDimension = cardFieldDimension;
        characterFieldCards = Arrays.asList(new ArenaCharacterCard[cardFieldDimension.getCharacterCardCount()]);
        skillFieldCards = Arrays.asList(new PutableSkillCard[cardFieldDimension.getSkillCardCount()]);
        hp = 80;
        hasPutLandCard = false;

        // TODO delete later
        for (int i = 0; i < elementLength; ++i) {
            currentElementValues[i] = 89;
            maxElementValues[i] = 89;
        }
    }

    /** 
     * @return int[]
     */
    public int[] getCurrentElementValues() {
        return currentElementValues;
    }

    /**
     * @param count number of card to draw from deck
     */
    public void drawCard(int count) {
        List<ActivableCard> subList = deck.subList(0, count);
        handCards.addAll(subList);
        subList.clear();
    }

    /**
     * Reset this player state
     */
    public void resetState() {
        hasPutLandCard = false;
        for (Element el : Element.values()) {
            currentElementValues[el.ordinal()] = maxElementValues[el.ordinal()];
        }
        characterFieldCards.stream().filter(acc -> acc != null).forEach(acc -> {
            acc.setHasAttacked(false);
            acc.setIsEnableToAttack(true);
        });
    }

    /**
     * Search for a specified card
     *
     * @param card specified card
     * @return true if found, false otherwise
     */
    public boolean hasCardOnField(ActivableCard card) {
        return characterFieldCards.stream().anyMatch(c -> c == card)
                || skillFieldCards.stream().anyMatch(c -> c == card);
    }

    /**
     * @return true if has any active character card, false otherwise
     */
    public boolean hasAnyArenaCharacterCard() {
        return characterFieldCards.stream().anyMatch(c -> c != null);
    }

    /**
     * Draw 1 card from deck and add it to hand
     */
    public void drawCard() {
        handCards.add(deck.remove(0));
    }

    /**
     * Add max element value for this player
     *
     * @param el specified element
     */
    public void addMaxElement(Element el) {
        addCurrentElement(el, 1);
        ++maxElementValues[el.ordinal()];
    }

    /**
     * Add a specified value to a specified element of this player
     *
     * @param el    specified element
     * @param count number of element value to be added
     */
    public void addCurrentElement(Element el, int count) {
        currentElementValues[el.ordinal()] += count;
    }

    /**
     * Adding a list of card to deck
     *
     * @param cards specified list of card to add
     */
    public void addToDeck(List<ActivableCard> cards) {
        deck.addAll(cards);
        totalDeckCount = deck.size();
    }

    /** 
     * @return CardFieldDimension
     */
    public CardFieldDimension getCardFieldDimension() {
        return cardFieldDimension;
    }

    /**
     * @return this player name
     */
    public String getName() {
        return name;
    }

    /**
     * @return this player hp
     */
    public int getHP() {
        return hp;
    }

    /**
     * @param element specified element
     * @return element value of this player
     */
    public int getCurrentElementValue(Element element) {
        return currentElementValues[element.ordinal()];
    }

    /**
     * @param element specified element
     * @return max element value of this player
     */
    public int getMaxElementValue(Element element) {
        return maxElementValues[element.ordinal()];
    }

    /**
     * @return this player's deck size
     */
    public int getCurrentDeckCount() {
        return deck.size();
    }

    /**
     * @return total number of card in deck
     */
    public int getTotalDeckCount() {
        return totalDeckCount;
    }

    /**
     * @return list of cards in hand
     */
    @Override
    public List<ActivableCard> getHandCards() {
        return handCards;
    }

    /**
     * @return all character cards
     */
    public List<ArenaCharacterCard> getCharacterCards() {
        return characterFieldCards;
    }

    /**
     * @return all skill cards
     */
    public List<PutableSkillCard> getSkillCards() {
        return skillFieldCards;
    }

    /**
     * Remove a character card from specified index column
     *
     * @param idx index column
     */
    public void removeCharacterCard(int idx) {
        characterFieldCards.set(idx, null);
    }

    /**
     * Remove a character card from a specified arena character card
     *
     * @param card specified arena character card
     */
    public boolean removeCharacterCard(ArenaCharacterCard card) {
        for (int i = 0; i < characterFieldCards.size(); ++i) {
            if (characterFieldCards.get(i) == card) {
                removeCharacterCard(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove a skill card from specified index column
     *
     * @param idx index column
     */
    public void removeSkillCard(int idx) {
        skillFieldCards.set(idx, null);
    }

    /**
     * Remove a character card from a specified putable skill card
     *
     * @param card specified putable skill card
     */
    public boolean removeSkillCard(PutableSkillCard card) {
        for (int i = 0; i < skillFieldCards.size(); ++i) {
            if (skillFieldCards.get(i) == card) {
                removeSkillCard(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Decrease HP by the amount of damage
     *
     * @param damage amount of damage dealt to this player
     */
    public void damage(int damage) {
        hp -= damage;
    }

    /** 
     * @param card
     * @return boolean
     */
    @Override
    public boolean hasOnHand(Card card) {
        return handCards.contains(card);
    }

    /** 
     * @param card
     * @return boolean
     */
    @Override
    public boolean canSpendPower(PoweredCard card) {
        return card.getPowerNeeded() <= currentElementValues[card.getElementType().ordinal()];
    }

    /** 
     * @param card
     */
    @Override
    public void spendPower(PoweredCard card) {
        addCurrentElement(card.getElementType(), -card.getPowerNeeded());
    }

    /** 
     * @return boolean
     */
    @Override
    public boolean canPutCharacterCard() {
        return characterFieldCards.contains(null);
    }

    /** 
     * @return boolean
     */
    @Override
    public boolean canPutSkillCard() {
        return skillFieldCards.contains(null);
    }

    /** 
     * @param card
     */
    @Override
    public void putCharacterCard(ArenaCharacterCard card) {
        characterFieldCards.set(characterFieldCards.indexOf(null), card);
    }

    /** 
     * @param card
     */
    @Override
    public void putSkillCard(PutableSkillCard card) {
        skillFieldCards.set(skillFieldCards.indexOf(null), card);
    }

    /** 
     * @return boolean
     */
    @Override
    public boolean hasPutLandCard() {
        return hasPutLandCard;
    }

    /** 
     * @param value
     */
    @Override
    public void setHasPutLandCard(boolean value) {
        hasPutLandCard = value;
    }

    /** 
     * @param card
     */
    @Override
    public void removeFromHand(ActivableCard card) {
        handCards.remove(card);
    }

}

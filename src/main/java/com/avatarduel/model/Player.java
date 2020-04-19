package com.avatarduel.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.avatarduel.controller.CardFieldDimension;
import com.avatarduel.model.card.ArenaCharacterCard;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.LandCard;
import com.avatarduel.model.card.PoweredCard;
import com.avatarduel.model.card.PutableCard;
import com.avatarduel.model.card.skill.PutableSkillCard;

/**
 * Represents a player
 */
public class Player {

    private int[] currentElementValue, maxElementValue;
    private int totalDeckCount, hp;
    private List<Card> deck, handCards;
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
        currentElementValue = new int[elementLength];
        maxElementValue = new int[elementLength];
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
            currentElementValue[i] = 89;
            maxElementValue[i] = 89;
        }
    }

    /**
     * @param count number of card to draw from deck
     */
    public void drawCard(int count) {
        List<Card> subList = deck.subList(0, count);
        handCards.addAll(subList);
        subList.clear();
    }

    /**
     * Reset this player state
     */
    public void resetState() {
        hasPutLandCard = false;
        for (Element el : Element.values()) {
            currentElementValue[el.ordinal()] = maxElementValue[el.ordinal()];
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
    public boolean hasCardOnField(Card card) {
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
        ++maxElementValue[el.ordinal()];
    }

    /**
     * Add a specified value to a specified element of this player
     *
     * @param el    specified element
     * @param count number of element value to be added
     */
    public void addCurrentElement(Element el, int count) {
        currentElementValue[el.ordinal()] += count;
    }

    /**
     * Adding a list of card to deck
     *
     * @param cards specified list of card to add
     */
    public void addToDeck(List<Card> cards) {
        deck.addAll(cards);
        totalDeckCount = deck.size();
    }

    /**
     * Add a character card to a field with specified index of column
     *
     * @param i    index of column
     * @param card specified card
     */
    private void putCharacterCard(int i, ArenaCharacterCard card) {
        characterFieldCards.set(i, card);
    }

    /**
     * Add a skill card to a field with specified index of column
     *
     * @param i    index of column
     * @param card specified card
     */
    private void putSkillCard(int i, PutableSkillCard card) {
        skillFieldCards.set(i, card);
    }

    /**
     * Add a land card to a field
     *
     * @param card specified card
     */
    private void putLandCard(LandCard card) {
        hasPutLandCard = true;
        addMaxElement(card.getElementType());
    }

    /**
     * Put a card from hand to field
     *
     * @param card specified card
     * @return true if success, false otherwise
     */
    public boolean putCard(Card card) {
        if (!canPutCard(card)) {
            return false;
        }
        handCards.remove(card);
        if (card instanceof LandCard) {
            putLandCard((LandCard) card);
            return true;
        }
        if (card instanceof PoweredCard) {
            addCurrentElement(card.getElementType(), -((PoweredCard) card).getPowerNeeded());
        }
        if (card instanceof PutableCard) {
            if (card instanceof CharacterCard) {
                for (int i = 0; i < characterFieldCards.size(); ++i) {
                    if (characterFieldCards.get(i) == null) {
                        putCharacterCard(i, ((CharacterCard) card).createArenaCard());
                        return true;
                    }
                }
            } else if (card instanceof PutableSkillCard) {
                for (int i = 0; i < skillFieldCards.size(); ++i) {
                    if (skillFieldCards.get(i) == null) {
                        putSkillCard(i, ((PutableSkillCard) card).createArenaCard());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @param card specified card
     * @return true if allowed to put a card
     */
    public boolean canPutCard(Card card) {
        if (!handCards.contains(card)) {
            return false;
        }
        if (card instanceof PutableCard) {
            boolean hasEmpty = false;
            if (card instanceof CharacterCard) {
                for (int i = 0; i < characterFieldCards.size(); ++i) {
                    if (characterFieldCards.get(i) == null) {
                        hasEmpty = true;
                        break;
                    }
                }
            } else if (card instanceof PutableSkillCard) {
                for (int i = 0; i < skillFieldCards.size(); ++i) {
                    if (skillFieldCards.get(i) == null) {
                        hasEmpty = true;
                        break;
                    }
                }
            }
            if (!hasEmpty) {
                return false;
            }
        }
        if (card instanceof LandCard) {
            return !hasPutLandCard;
        } else if (card instanceof PoweredCard) {
            return ((PoweredCard) card).getPowerNeeded() <= currentElementValue[card.getElementType().ordinal()];
        }
        return false;
    }

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
        return currentElementValue[element.ordinal()];
    }

    /**
     * @param element specified element
     * @return max element value of this player
     */
    public int getMaxElementValue(Element element) {
        return maxElementValue[element.ordinal()];
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
    public List<Card> getHandCards() {
        return handCards;
    }

    /**
     * @param idx index column
     * @return card in specified row and column in field
     */
    public List<ArenaCharacterCard> getCharacterCards() {
        return characterFieldCards;
    }

    /**
     * @param idx index column
     * @return card in specified row and column in field
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

}

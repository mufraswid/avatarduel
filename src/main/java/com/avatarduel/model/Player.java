package com.avatarduel.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.avatarduel.Constants;
import com.avatarduel.model.card.ArenaCharacterCard;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.LandCard;
import com.avatarduel.model.card.PoweredCard;
import com.avatarduel.model.card.PutableCard;

/**
 * Represents a player
 */
public class Player {

    private int[] currentElementValue, maxElementValue;
    private int totalDeckCount, hp;
    private List<Card> deck, handCards;
    private Card[][] fieldCards;
    private String name;
    private boolean hasPutLandCard;

    /**
     * Constructor for player with a specified name
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        int elementLength = Element.values().length;
        currentElementValue = new int[elementLength];
        maxElementValue = new int[elementLength];
        totalDeckCount = 0;
        deck = new ArrayList<>();
        handCards = new ArrayList<>();
        fieldCards = new Card[Constants.CARD_ROW][Constants.CARD_COLUMN];
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
        int i = 0;
        for (int j = 0; j < Constants.CARD_COLUMN; ++j) {
            if (fieldCards[i][j] != null) {
                ArenaCharacterCard acc = (ArenaCharacterCard) fieldCards[i][j];
                acc.setHasAttacked(false);
                acc.setIsEnableToAttack(true);
            }
        }
    }

    /**
     * Search for a specified card
     *
     * @param card specified card
     * @return true if found, false otherwise
     */
    public boolean hasCardOnField(Card card) {
        return Arrays.stream(fieldCards[0]).anyMatch(c -> c == card)
                || Arrays.stream(fieldCards[1]).anyMatch(c -> c == card);
    }

    /**
     * @return true if has any active character card, false otherwise
     */
    public boolean hasActiveCharacterCard() {
        return Arrays.stream(fieldCards[0]).anyMatch(c -> c != null);
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
     * Add a card to a field with specified row and column
     *
     * @param i    number of row
     * @param j    number of column
     * @param card specified card
     */
    private void putCard(int i, int j, Card card) {
        fieldCards[i][j] = card;
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
            int i = card instanceof CharacterCard ? 0 : 1;
            for (int j = 0; j < fieldCards[i].length; ++j) {
                if (fieldCards[i][j] == null) {
                    putCard(i, j, ((PutableCard) card).createArenaCard());
                    return true;
                }
            }
        }
        return true;
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
            int i = card instanceof CharacterCard ? 0 : 1;
            for (int j = 0; j < fieldCards[i].length; ++j) {
                if (fieldCards[i][j] == null) {
                    hasEmpty = true;
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
     * @param row number of row
     * @param col number of column
     * @return card in specified row and column in field
     */
    public Card getFieldCard(int row, int col) {
        return fieldCards[row][col];
    }

    /**
     * Remove a card from specified row and column
     *
     * @param row number of row
     * @param col number of col
     */
    public void removeFieldCard(int row, int col) {
        fieldCards[row][col] = null;
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

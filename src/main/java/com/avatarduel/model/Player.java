package com.avatarduel.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.avatarduel.Constants;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.LandCard;

public class Player {

    private int[] currentElementValue, maxElementValue;
    private int totalDeckCount, hp;
    private List<Card> deck, handCards;
    private Card[][] fieldCards;
    private String name;
    private boolean hasPutLandCard;

    public Player(String name) {
        this.name = name;
        int elementLenght = Element.values().length;
        currentElementValue = new int[elementLenght];
        maxElementValue = new int[elementLenght];
        totalDeckCount = 0;
        deck = new ArrayList<>();
        handCards = new ArrayList<>();
        fieldCards = new Card[Constants.CARD_ROW][Constants.CARD_COLUMN];
        hp = 80;
        hasPutLandCard = false;
    }

    public void drawCard(int count) {
        List<Card> subList = deck.subList(0, count);
        handCards.addAll(subList);
        subList.clear();
    }

    public void drawCard() {
        handCards.add(deck.remove(0));
        hasPutLandCard = false;
    }

    public void addElement(Element el) {
        int i = el.ordinal();
        ++currentElementValue[i];
        ++maxElementValue[i];
    }

    public void addToDeck(List<Card> cards) {
        deck.addAll(cards);
        totalDeckCount = deck.size();
    }

    private void putCard(int i, int j, Card card) {
        handCards.remove(card);
        fieldCards[i][j] = card instanceof CharacterCard ? ((CharacterCard) card).createActiveCard() : card;
    }

    private boolean putLandCard(LandCard card) {
        if (hasPutLandCard) {
            return false;
        }
        handCards.remove(card);
        hasPutLandCard = true;
        addElement(card.getElementType());
        return true;
    }

    public boolean putCard(Card card) {
        if (!handCards.contains(card)) {
            return false;
        }
        if (card instanceof LandCard) {
            return putLandCard((LandCard) card);
        }
        int i = card instanceof CharacterCard ? 0 : 1;
        for (int j = 0; j < fieldCards[i].length; ++j) {
            if (fieldCards[i][j] == null) {
                putCard(i, j, card);
                return true;
            }
        }
        return false;
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return hp;
    }

    public int getCurrentElementValue(Element element) {
        return currentElementValue[element.ordinal()];
    }

    public int getMaxElementValue(Element element) {
        return maxElementValue[element.ordinal()];
    }

    public int getCurrentDeckCount() {
        return deck.size();
    }

    public int getTotalDeckCount() {
        return totalDeckCount;
    }

    public List<Card> getHandCards() {
        return handCards;
    }

    public Card getFieldCard(int row, int col) {
        return fieldCards[row][col];
    }

}
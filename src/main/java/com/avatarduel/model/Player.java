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

public class Player {

    private int[] currentElementValue, maxElementValue;
    private int totalDeckCount, hp;
    private List<Card> deck, handCards;
    private Card[][] fieldCards;
    private String name;
    private boolean hasPutLandCard;

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

    public void drawCard(int count) {
        List<Card> subList = deck.subList(0, count);
        handCards.addAll(subList);
        subList.clear();
    }

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

    public boolean hasCardOnField(Card card) {
        return Arrays.stream(fieldCards[0]).anyMatch(c -> c == card)
                || Arrays.stream(fieldCards[1]).anyMatch(c -> c == card);
    }

    public boolean hasActiveCharacterCard() {
        return Arrays.stream(fieldCards[0]).anyMatch(c -> c != null);
    }

    public void drawCard() {
        handCards.add(deck.remove(0));
    }

    public void addMaxElement(Element el) {
        addCurrentElement(el, 1);
        ++maxElementValue[el.ordinal()];
    }

    public void addCurrentElement(Element el, int count) {
        currentElementValue[el.ordinal()] += count;
    }

    public void addToDeck(List<Card> cards) {
        deck.addAll(cards);
        totalDeckCount = deck.size();
    }

    private void putCard(int i, int j, Card card) {
        fieldCards[i][j] = card;
    }

    private void putLandCard(LandCard card) {
        hasPutLandCard = true;
        addMaxElement(card.getElementType());
    }

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
        return (card instanceof LandCard && !hasPutLandCard) || (card instanceof PoweredCard
                && ((PoweredCard) card).getPowerNeeded() <= currentElementValue[card.getElementType().ordinal()]);
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

    public void removeFieldCard(int row, int col) {
        fieldCards[row][col] = null;
    }

    public void damage(int damage) {
        hp -= damage;
    }

}
package com.avatarduel.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.avatarduel.Constants;
import com.avatarduel.controller.GameController;
import com.avatarduel.model.card.Card;
import com.avatarduel.view.ViewPosition;

public class Player {

    private ViewPosition position;
    private int[] currentElementValue, maxElementValue;
    private int totalDeckCount, hp;
    private List<Card> deck, handCards;
    private Card[][] placedCards;
    private String name;

    public Player(String name, ViewPosition viewPosition) {
        this.name = name;
        this.position = viewPosition;
        this.currentElementValue = new int[Element.values().length];
        this.maxElementValue = new int[Element.values().length];
        totalDeckCount = 0;
        deck = new ArrayList<>();
        handCards = new ArrayList<>();
        placedCards = new Card[Constants.CARD_ROW][Constants.CARD_COLUMN];
        hp = 80;
    }

    public boolean isPlaying() {
        return this == GameController.getInstance().getCurrentPlayerTurn();
    }

    public void drawCard(int count) {
        List<Card> subList = deck.subList(0, count);
        handCards.addAll(subList);
        deck.removeAll(subList);
    }

    public void drawCard() {
        handCards.add(deck.remove(0));
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

    public void putCard(int i, int j, Card card) {
        handCards.remove(card);
        placedCards[i][j] = card;
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

    public ViewPosition getPosition() {
        return position;
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

    public Card getPlacedCard(int row, int col) {
        return placedCards[row][col];
    }

}
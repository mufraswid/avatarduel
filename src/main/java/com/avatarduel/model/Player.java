package com.avatarduel.model;

import java.util.ArrayList;
import java.util.List;

import com.avatarduel.Constants;
import com.avatarduel.controller.Game;
import com.avatarduel.model.card.Card;
import com.avatarduel.view.ViewPosition;

public class Player {

    private ViewPosition position;
    private int[] currentElementValue, maxElementValue;
    private int currentDeckCount, totalDeckCount, hp;
    private List<Card> cardList;
    private Card[][] placedCards;
    private String name;

    public Player(String name, ViewPosition viewPosition) {
        this.position = viewPosition;
        this.currentElementValue = new int[Element.values().length];
        this.maxElementValue = new int[Element.values().length];
        currentDeckCount = 0;
        totalDeckCount = 0;
        cardList = new ArrayList<>();
        placedCards = new Card[Constants.CARD_ROW][Constants.CARD_COLUMN];
        hp = 80;
        this.name = name;

        // TODO delete later
        placedCards[0][0] = Game.getInstance().getCards().get(56);
        placedCards[0][1] = Game.getInstance().getCards().get(97);
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
        return currentDeckCount;
    }

    public int getTotalDeckCount() {
        return totalDeckCount;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public Card getPlacedCard(int row, int col) {
        return placedCards[row][col];
    }

}
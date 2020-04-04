package com.avatarduel.model;

import java.util.List;

import com.avatarduel.model.card.Card;
import com.avatarduel.view.ViewPosition;

public class Player {

    private ViewPosition position;
    private int[] currentElementValue, maxElementValue;
    private int currentDeckCount, totalDeckCount;
    private List<Card> cardList;
    private Card[][] placedCards;

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
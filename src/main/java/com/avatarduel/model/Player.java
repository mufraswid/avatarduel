package com.avatarduel.model;

import java.util.ArrayList;
import java.util.List;

import com.avatarduel.Constants;
import com.avatarduel.controller.Game;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.view.ViewPosition;

/**
 * Kelas Player: kelas yang memodelkan konsep pemain pada permainan
 */

public class Player {

    private ViewPosition position;
    private int[] currentElementValue, maxElementValue;
    private int totalDeckCount, hp;
    private List<Card> deck, handCards;
    private Card[][] placedCards;
    private String name;

    public Player(String name, ViewPosition viewPosition) {
        this.position = viewPosition;
        this.currentElementValue = new int[Element.values().length];
        this.maxElementValue = new int[Element.values().length];
        totalDeckCount = 0;
        deck = new ArrayList<>();
        handCards = new ArrayList<>();
        placedCards = new Card[Constants.CARD_ROW][Constants.CARD_COLUMN];
        hp = 80;
        this.name = name;

        // TODO delete later
        placedCards[0][4] = Game.getInstance().getCards().get(56);
        ((CharacterCard) placedCards[0][4]).switchPosition();
        placedCards[1][5] = Game.getInstance().getCards().get(97);
    }

    //#region getter
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
    //#end region

}
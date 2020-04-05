package com.avatarduel.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.avatarduel.Constants;
import com.avatarduel.controller.card.CardController;
import com.avatarduel.controller.card.CharacterCardController;
import com.avatarduel.model.Element;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.view.ViewPosition;

public class PlayerController {

    private ViewPosition position;
    private int[] currentElementValue, maxElementValue;
    private int totalDeckCount, hp;
    private List<CardController> deck, handCards;
    private CardController[][] placedCards;
    private Player player;

    public PlayerController(Player player, ViewPosition viewPosition) {
        this.player = player;
        this.position = viewPosition;
        this.currentElementValue = new int[Element.values().length];
        this.maxElementValue = new int[Element.values().length];
        totalDeckCount = 0;
        deck = new ArrayList<>();
        handCards = new ArrayList<>();
        placedCards = new CardController[Constants.CARD_ROW][Constants.CARD_COLUMN];
        hp = 80;
    }

    public boolean isPlaying() {
        return this == GameController.getInstance().getCurrentPlayerTurn();
    }

    public void drawCard(int count) {
        List<CardController> subList = deck.subList(0, count);
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
        deck.addAll(cards.stream().map(c -> c instanceof CharacterCard ? new CharacterCardController((CharacterCard) c)
                : new CardController(c)).collect(Collectors.toList()));
        totalDeckCount = deck.size();
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public String getName() {
        return player.getName();
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

    public List<CardController> getHandCards() {
        return handCards;
    }

    public CardController getPlacedCard(int row, int col) {
        return placedCards[row][col];
    }

}
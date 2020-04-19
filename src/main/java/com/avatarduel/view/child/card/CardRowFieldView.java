package com.avatarduel.view.child.card;

import java.util.List;

import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.card.Card;

public class CardRowFieldView extends CardView {

    private CardEventListener listener;

    public CardRowFieldView(int column, CardEventListener listener) {
        super("100", "100");
        this.listener = listener;
        double[] cols = new double[column];
        for (int i = 0; i < column; ++i) {
            cols[i] = 100d / column;
        }
        setGridPercentages(cols, new double[] { 100 });
    }

    /** 
     * @param cards
     */
    public void renderCards(List<? extends Card> cards) {
        getChildren().clear();
        int i = 0;
        for (Card card : cards) {
            PlacedCardView placedCardView = new PlacedCardView(listener);
            add(placedCardView, i++, 0);
            placedCardView.render(card);
        }
    }

}
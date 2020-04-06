package com.avatarduel.view.child.card;

import com.avatarduel.Constants;
import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import com.avatarduel.view.GridView;
import com.avatarduel.view.ViewPosition;

public class CardFieldView extends GridView {

    private ViewPosition position;
    private CardEventListener listener;

    public CardFieldView(ViewPosition position, CardEventListener listener) {
        super("15,15,15,15,15,15,15", "50,50");
        this.position = position;
        this.listener = listener;
    }

    public void render(Player player) {
        getChildren().clear();
        for (int i = 0; i < Constants.CARD_ROW; ++i) {
            for (int j = 0; j < Constants.CARD_COLUMN; ++j) {
                Card card = player.getFieldCard(i, j);
                int row = position == ViewPosition.BOTTOM ? i : (i + 1) % 2;
                PlacedCardView placedCardView = new PlacedCardView(listener);
                add(placedCardView, j, row);
                placedCardView.render(card == null ? null : player.getFieldCard(i, j));
            }
        }
    }

}
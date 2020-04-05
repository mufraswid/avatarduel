package com.avatarduel.view.child.card;

import com.avatarduel.Constants;
import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import com.avatarduel.view.GridView;
import com.avatarduel.view.ViewPosition;

public class CardFieldView extends GridView {

    public CardFieldView(Player player, ViewPosition position, CardEventListener listener) {
        super("15,15,15,15,15,15,15", "50,50");
        for (int i = 0; i < Constants.CARD_ROW; ++i) {
            for (int j = 0; j < Constants.CARD_COLUMN; ++j) {
                Card card = player.getPlacedCard(i, j);
                int row = position == ViewPosition.BOTTOM ? i : (i + 1) % 2;
                add(new PlacedCardView(card == null ? null : player.getPlacedCard(i, j), listener), j, row);
            }
        }
    }

}
package com.avatarduel.view.child.card;

import com.avatarduel.Constants;
import com.avatarduel.controller.PlayerController;
import com.avatarduel.view.GridView;
import com.avatarduel.view.ViewPosition;
import com.avatarduel.view.child.PlayerRenderer;

public class CardFieldView extends GridView implements PlayerRenderer {

    public PlacedCardView[][] placedCardViews;
    public ViewPosition position;

    public CardFieldView(ViewPosition position) {
        super("15,15,15,15,15,15,15", "50,50");
        this.position = position;
        placedCardViews = new PlacedCardView[Constants.CARD_ROW][Constants.CARD_COLUMN];
        initGUI();
    }

    @Override
    public void initGUI() {
        for (int i = 0; i < Constants.CARD_ROW; ++i) {
            for (int j = 0; j < Constants.CARD_COLUMN; ++j) {
                int row = position == ViewPosition.BOTTOM ? i : (i + 1) % 2;
                add(placedCardViews[i][j] = new PlacedCardView(), j, row);
            }
        }
    }

    @Override
    public void renderPlayer(PlayerController player) {
        for (int i = 0; i < Constants.CARD_ROW; ++i) {
            for (int j = 0; j < Constants.CARD_COLUMN; ++j) {
                placedCardViews[i][j].renderCard(player == null ? null : player.getPlacedCard(i, j));
            }
        }
    }

}
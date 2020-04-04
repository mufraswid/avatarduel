package com.avatarduel.view.child.card;

import com.avatarduel.Constants;
import com.avatarduel.model.Player;
import com.avatarduel.view.ViewPosition;

public class CardFieldView extends CardView {

    private PlacedCardView[][] placedCardViews;
    private Player player;

    public CardFieldView(Player player) {
        super(null, "15,15,15,15,15,15,15", "50,50");
        placedCardViews = new PlacedCardView[Constants.CARD_ROW][Constants.CARD_COLUMN];
        this.player = player;
        initGUI();
    }

    @Override
    public void initGUI() {
        for (int i = 0; i < Constants.CARD_ROW; ++i) {
            for (int j = 0; j < Constants.CARD_COLUMN; ++j) {
                int row = player.getPosition() == ViewPosition.BOTTOM ? i : (i + 1) % 2;
                add(placedCardViews[i][j] = new PlacedCardView(null), j, row);
            }
        }
        // if (player.getPosition() == ViewPosition.BOTTOM) {
        //     setStyle("-fx-border-style: solid none none none; -fx-border-width: 5; -fx-border-color: black;");
        // }
        refreshView();
    }

    @Override
    public void setClosed(boolean closed) {
        super.setClosed(closed);
        for (PlacedCardView[] views : placedCardViews) {
            for (ClosableCard card : views) {
                card.setClosed(closed);
            }
        }
    }

    @Override
    public void refreshView() {
        for (int i = 0; i < Constants.CARD_ROW; ++i) {
            for (int j = 0; j < Constants.CARD_COLUMN; ++j) {
                placedCardViews[i][j].setCard(player.getPlacedCard(i, j));
            }
        }
    }

}
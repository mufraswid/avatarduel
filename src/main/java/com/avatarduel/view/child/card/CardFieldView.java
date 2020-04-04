package com.avatarduel.view.child.card;

import com.avatarduel.Constants;
import com.avatarduel.model.Player;
import com.avatarduel.view.GridView;
import com.avatarduel.view.RefreshableView;

public class CardFieldView extends GridView implements ClosableCard, RefreshableView {

    private PlacedCardView[][] placedCardViews;
    private Player player;

    public CardFieldView(Player player) {
        super("14,14,14,14,14,14,14", "50,50");
        placedCardViews = new PlacedCardView[Constants.CARD_ROW][Constants.CARD_COLUMN];
        this.player = player;
        refreshView();
    }

    @Override
    public void initGUI() {
        for (int i = 0; i < Constants.CARD_ROW; ++i) {
            for (int j = 0; j < Constants.CARD_COLUMN; ++j) {
                add(placedCardViews[i][j], j, i);
            }
        }
    }

    @Override
    public void setClosed(boolean closed) {
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
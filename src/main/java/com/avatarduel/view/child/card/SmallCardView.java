package com.avatarduel.view.child.card;

import com.avatarduel.controller.Game;
import com.avatarduel.model.card.Card;

import javafx.scene.Cursor;

public class SmallCardView extends CardView {

    public SmallCardView(Card card) {
        super(card, "100", "100");
        setClosed(true);
        initGUI();
    }

    @Override
    public void initGUI() {
        hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (!hasCard()) {
                return;
            }
            if (newValue) {
                Game.getInstance().getMainView().getBigCardView().setCard(getCard());
                Game.getInstance().getScene().setCursor(Cursor.HAND);
            } else {
                Game.getInstance().getScene().setCursor(Cursor.DEFAULT);
            }
        });

        // TODO: Implement
    }

    @Override
    public void refreshView() {
        super.refreshView();
    }

}
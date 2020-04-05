package com.avatarduel.view.child.card;

import com.avatarduel.Constants;
import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.Player;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class HandCardFieldView extends ScrollPane {

    public HandCardFieldView(Player player, CardEventListener listener) {
        HBox hbox = new HBox();
        hbox.setSpacing(Constants.GAP);
        setContent(hbox);
        setFitToHeight(true);
        player.getHandCards().stream().forEach(card -> {
            SmallCardView smallCardView = new SmallCardView(card, listener);
            // smallCardView.renderCard(player.isPlaying() ? card : GameController.getInstance().getClosedCard());
            hbox.getChildren().add(smallCardView);
        });
    }

}
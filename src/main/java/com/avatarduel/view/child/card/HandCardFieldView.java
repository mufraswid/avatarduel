package com.avatarduel.view.child.card;

import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

/**
 * Display the card on hand for each player
 */
public class HandCardFieldView extends ScrollPane {

    private HBox hbox;
    private CardEventListener listener;

    /**
     * Constructor
     *
     * @param listener card event listener
     */
    public HandCardFieldView(CardEventListener listener) {
        this.listener = listener;
        hbox = new HBox();
        hbox.setSpacing(10);
        setContent(hbox);
        setFitToHeight(true);
    }

    /**
     * @param player specified player
     * @param closed a closed card
     */
    public void render(Player player, Card closed) {
        hbox.getChildren().clear();
        player.getHandCards().stream().forEach(card -> {
            SmallCardView smallCardView = new SmallCardView(listener);
            smallCardView.render(closed == null ? card : closed);
            hbox.getChildren().add(smallCardView);
        });
    }

}

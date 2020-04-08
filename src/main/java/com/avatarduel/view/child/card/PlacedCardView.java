package com.avatarduel.view.child.card;

import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.CardPosition;
import com.avatarduel.model.card.ArenaCharacterCard;
import com.avatarduel.model.card.Card;

public class PlacedCardView extends CardView {

    private CardEventListener listener;

    public PlacedCardView(CardEventListener listener) {
        super("100", "100");
        this.listener = listener;
    }

    public void render(Card card) {
        getChildren().clear();
        if (card != null) {
            SmallCardView smallCardView = new SmallCardView(listener);
            smallCardView.render(card);
            add(smallCardView, 0, 0);
            if (card instanceof ArenaCharacterCard) {
                ArenaCharacterCard ccc = (ArenaCharacterCard) card;
                boolean isDefense = ccc.getPosition() == CardPosition.DEFENSE;
                smallCardView.setRotate(isDefense ? -90 : 0);
            }
        }
    }

}
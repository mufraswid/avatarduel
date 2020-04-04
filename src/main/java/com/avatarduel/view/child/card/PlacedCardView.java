package com.avatarduel.view.child.card;

import com.avatarduel.controller.CardController;
import com.avatarduel.model.CardPosition;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;

public class PlacedCardView extends CardView {

    public SmallCardView smallCardView;
    public CardPosition cardPosition;

    public PlacedCardView() {
        super("100", "100");
        smallCardView = new SmallCardView();
        initGUI();
    }

    public void setPosition(CardPosition cardPosition) {
        if (this.cardPosition != cardPosition) {
            boolean isDefense = cardPosition == CardPosition.DEFENSE;
            // double horizontal = isDefense ? 0 : Constants.GAP;
            // double vertical = isDefense ? Constants.GAP : 0;
            // setPadding(new Insets(vertical, horizontal, vertical, horizontal));
            smallCardView.setRotate(isDefense ? -90 : 0);
            this.cardPosition = cardPosition;
        }
    }

    @Override
    public void initGUI() {
    }

    @Override
    public void renderCard(CardController cc) {
        getChildren().clear();
        if (cc != null) {
            Card card = cc.getCard();
            add(smallCardView, 0, 0);
            smallCardView.renderCard(cc);
            if (card instanceof CharacterCard) {
                setPosition(((CharacterCard) card).getPosition());
            }
        }
    }

}
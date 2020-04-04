package com.avatarduel.view.child.card;

import com.avatarduel.model.CardPosition;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;

public class PlacedCardView extends CardView {

    private SmallCardView smallCardView;
    private CardPosition cardPosition;

    public PlacedCardView(Card card) {
        super(card, "100", "100");
        smallCardView = new SmallCardView(card);
        initGUI();
    }

    private void setPosition(CardPosition cardPosition) {
        if (this.cardPosition != cardPosition) {
            boolean isDefense = cardPosition == CardPosition.DEFENSE;
            // double horizontal = isDefense ? 0 : Constants.GAP;
            // double vertical = isDefense ? Constants.GAP : 0;
            // setPadding(new Insets(vertical, horizontal, vertical, horizontal));
            smallCardView.setRotate(isDefense ? -90 : 0);
            this.cardPosition = cardPosition;
        }
    }

    public CardPosition getPosition() {
        return this.cardPosition;
    }

    @Override
    public void setCard(Card card) {
        setCard(card, CardPosition.ATTACK);
    }

    public void setCard(Card card, CardPosition cardPosition) {
        setPosition(cardPosition);
        smallCardView.setCard(card);
        super.setCard(card);
    }

    public void switchPosition() {
        CardPosition pos = getPosition();
        setPosition(pos == CardPosition.ATTACK ? CardPosition.DEFENSE : CardPosition.ATTACK);
    }

    @Override
    public void initGUI() {
        refreshView();
    }

    @Override
    public void refreshView() {
        getChildren().clear();
        if (hasCard()) {
            add(smallCardView, 0, 0);
            smallCardView.refreshView();
            if (card instanceof CharacterCard) {
                setPosition(((CharacterCard) card).getPosition());
            }
        }
    }

    @Override
    public void setClosed(boolean closed) {
        smallCardView.setClosed(closed);
    }

}
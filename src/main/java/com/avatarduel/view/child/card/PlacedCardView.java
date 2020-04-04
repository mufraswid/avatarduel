package com.avatarduel.view.child.card;

import com.avatarduel.Constants;
import com.avatarduel.model.CardPosition;
import com.avatarduel.model.card.Card;

import javafx.geometry.Insets;

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
            double horizontal = isDefense ? 0 : Constants.GAP;
            double vertical = isDefense ? Constants.GAP : 0;
            setPadding(new Insets(vertical, horizontal, vertical, horizontal));
            smallCardView.setRotate(isDefense ? -90 : 90);
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
        add(smallCardView, 0, 0);
    }

    @Override
    public void refreshView() {
        smallCardView.refreshView();
    }

    @Override
    public void setClosed(boolean closed) {
        smallCardView.setClosed(closed);
    }

}
package com.avatarduel.view.child.card;

import com.avatarduel.controller.card.CardController;
import com.avatarduel.model.card.Card;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.status.StatusViewFactory;

public class DescriptionCardView extends CardView {

    public DefaultText descriptionText;
    public CardView statusCardView;

    public DescriptionCardView() {
        super("100", "80,20");
        descriptionText = new DefaultText();
        initGUI();
    }

    @Override
    public void initGUI() {
    }

    @Override
    public void renderCard(CardController cc) {
        if (cc != null) {
            Card card = cc.getCard();
            statusCardView = StatusViewFactory.createStatusView(cc, false);
            descriptionText.setText(card.getDescription());
            descriptionText.text.wrappingWidthProperty().bind(widthProperty().subtract(5)); // .subtract(Constants.GAP)
            getChildren().clear();
            add(descriptionText, 0, 0);
            if (statusCardView != null) {
                statusCardView.renderCard(cc);
                add(statusCardView, 0, 1);
            }
        }
    }

}
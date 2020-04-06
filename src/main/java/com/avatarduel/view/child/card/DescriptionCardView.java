package com.avatarduel.view.child.card;

import com.avatarduel.model.card.Card;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.status.StatusViewFactory;

public class DescriptionCardView extends CardView {

    public DescriptionCardView() {
        super("100", "80,20");
    }

    public void renderCard(Card card) {
        getChildren().clear();
        DefaultText descriptionText = new DefaultText();
        CardView statusCardView = StatusViewFactory.createStatusView(card, false);
        descriptionText.setText(card.getDescription());
        descriptionText.getText().wrappingWidthProperty().bind(widthProperty().subtract(5)); // .subtract(Constants.GAP)
        add(descriptionText, 0, 0);
        if (statusCardView != null) {
            add(statusCardView, 0, 1);
        }
    }

}
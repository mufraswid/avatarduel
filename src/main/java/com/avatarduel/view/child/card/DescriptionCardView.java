package com.avatarduel.view.child.card;

import com.avatarduel.model.card.Card;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.status.StatusViewFactory;

/**
 * Display card description
 */
public class DescriptionCardView extends CardView {

    /**
     * Constructor
     */
    public DescriptionCardView() {
        super("100", "80,20");
    }

    /**
     * @param card the specified card
     */
    public void renderCard(Card card) {
        getChildren().clear();
        DefaultText descriptionText = new DefaultText();
        CardView statusCardView = StatusViewFactory.createStatusView(card, false);
        descriptionText.setText(card.getFullDescription());
        descriptionText.getText().wrappingWidthProperty().bind(widthProperty().subtract(5));
        add(descriptionText, 0, 0);
        if (statusCardView != null) {
            add(statusCardView, 0, 1);
        }
    }

}

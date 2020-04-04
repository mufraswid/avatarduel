package com.avatarduel.view.child.card;

import com.avatarduel.model.card.Card;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.status.StatusViewFactory;

public class DescriptionCardView extends CardView {

    private DefaultText descriptionText;
    private CardView statusCardView;

    public DescriptionCardView(Card card) {
        super(card, "100", "80,20");
        descriptionText = new DefaultText();
        initGUI();
    }

    @Override
    public void initGUI() {
        refreshView();
    }

    @Override
    public void refreshView() {
        if (hasCard()) {
            statusCardView = StatusViewFactory.createStatusView(card);
            descriptionText.setText(card.getDescription());
            descriptionText.wrappingWidthProperty().bind(widthProperty().subtract(5)); // .subtract(Constants.GAP)
            getChildren().clear();
            add(descriptionText, 0, 0);
            if (statusCardView != null) {
                add(statusCardView, 0, 1);
            }
        }
    }

}
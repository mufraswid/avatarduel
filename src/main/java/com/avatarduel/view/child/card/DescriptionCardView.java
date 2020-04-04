package com.avatarduel.view.child.card;

import com.avatarduel.Constants;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.skill.SkillCard;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.status.CharacterStatusView;
import com.avatarduel.view.child.card.status.SkillStatusView;

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
            if (card instanceof SkillCard) {
                statusCardView = new SkillStatusView((SkillCard) card);
            } else if (card instanceof CharacterCard) {
                statusCardView = new CharacterStatusView((CharacterCard) card);
            }
            descriptionText.setText(card.getDescription());
            descriptionText.wrappingWidthProperty().bind(widthProperty().subtract(Constants.GAP));
            getChildren().clear();
            add(descriptionText, 0, 0);
            if (statusCardView != null) {
                add(statusCardView, 0, 1);
            }
        }
    }

}
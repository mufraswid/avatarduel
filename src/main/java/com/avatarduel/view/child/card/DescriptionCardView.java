package com.avatarduel.view.child.card;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.LandCard;
import com.avatarduel.model.card.skill.AuraSkillCard;
import com.avatarduel.model.card.skill.DestroySkillCard;
import com.avatarduel.model.card.skill.PowerUpSkillCard;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.status.StatusViewFactory;

public class DescriptionCardView extends CardView {

    public DescriptionCardView() {
        super("100", "80,20");
    }

    private String getDescription(Card card) {
        String text = "";
        if (card instanceof CharacterCard) {
            text += "Character Card";
        } else if (card instanceof LandCard) {
            text += "Land Card";
        } else if (card instanceof AuraSkillCard) {
            text += "Aura Skill Card";
        } else if (card instanceof DestroySkillCard) {
            text += "Destroy Skill Card";
        } else if (card instanceof PowerUpSkillCard) {
            text += "Power Up Skill Card";
        }
        return text + "\n" + card.getDescription();
    }

    public void renderCard(Card card) {
        getChildren().clear();
        DefaultText descriptionText = new DefaultText();
        CardView statusCardView = StatusViewFactory.createStatusView(card, false);
        descriptionText.setText(getDescription(card));
        descriptionText.getText().wrappingWidthProperty().bind(widthProperty().subtract(5)); // .subtract(Constants.GAP)
        add(descriptionText, 0, 0);
        if (statusCardView != null) {
            add(statusCardView, 0, 1);
        }
    }

}
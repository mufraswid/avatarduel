package com.avatarduel.view.child.card.status;

import com.avatarduel.Constants;
import com.avatarduel.model.card.skill.SkillCard;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.CardView;

public class SkillStatusView extends CardView {

    public SkillStatusView(SkillCard card, boolean small) {
        super("100", "100");
        DefaultText powerText = new DefaultText();
        if (small) {
            powerText.setSize(Constants.SMALL_FONT_SIZE);
        }
        powerText.setText("PWR " + card.getPowerNeeded());
        add(powerText, 0, 0);
    }

}
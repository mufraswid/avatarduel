package com.avatarduel.view.child.card.status;

import com.avatarduel.Constants;
import com.avatarduel.model.card.skill.SkillCard;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.CardView;

public class SkillStatusView extends CardView {

    private DefaultText powerText;

    public SkillStatusView(boolean small) {
        super("100", "100");
        powerText = new DefaultText();
        if (small) {
            powerText.setSize(Constants.SMALL_FONT_SIZE);
        }
        add(powerText, 0, 0);
    }

    public void render(SkillCard card) {
        powerText.setText("PWR " + card.getPowerNeeded());
    }

}
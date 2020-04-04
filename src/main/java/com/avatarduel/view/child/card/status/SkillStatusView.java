package com.avatarduel.view.child.card.status;

import com.avatarduel.Constants;
import com.avatarduel.model.card.skill.SkillCard;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.CardView;

public class SkillStatusView extends CardView {

    private DefaultText powerText;

    public SkillStatusView(SkillCard card, boolean small) {
        super(card, "100", "100");
        powerText = new DefaultText();
        if (small) {
            powerText.setSize(Constants.SMALL_FONT_SIZE);
        }
        initGUI();
    }

    public SkillStatusView(SkillCard card) {
        this(card, false);
    }

    @Override
    public void initGUI() {
        add(powerText, 0, 0);
        refreshView();
    }

    @Override
    public void refreshView() {
        if (hasCard()) {
            SkillCard card = (SkillCard) this.card;
            powerText.setText("PWR " + card.getPowerNeeded());
        }
    }

}
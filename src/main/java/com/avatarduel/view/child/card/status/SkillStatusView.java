package com.avatarduel.view.child.card.status;

import com.avatarduel.Constants;
import com.avatarduel.controller.card.CardController;
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
        initGUI();
    }

    public SkillStatusView() {
        this(false);
    }

    @Override
    public void initGUI() {
        add(powerText, 0, 0);
    }

    @Override
    public void renderCard(CardController cc) {
        if (cc != null) {
            SkillCard card = (SkillCard) cc.getCard();
            powerText.setText("PWR " + card.getPowerNeeded());
        }
    }

}
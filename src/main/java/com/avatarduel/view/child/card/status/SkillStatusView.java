package com.avatarduel.view.child.card.status;

import com.avatarduel.model.card.skill.SkillCard;
import com.avatarduel.view.child.card.CardView;

import javafx.scene.text.Text;

public class SkillStatusView extends CardView {

    private Text powerText;

    public SkillStatusView(SkillCard card) {
        super(card, "100", "100");
        powerText = new Text();
        initGUI();
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
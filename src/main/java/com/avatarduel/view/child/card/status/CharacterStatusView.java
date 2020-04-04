package com.avatarduel.view.child.card.status;

import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.CardView;

public class CharacterStatusView extends CardView {

    private DefaultText attackText, defenseText, powerText;

    public CharacterStatusView(CharacterCard card) {
        super(card, "34,34,34", "100");
        attackText = new DefaultText();
        defenseText = new DefaultText();
        powerText = new DefaultText();
        initGUI();
    }

    @Override
    public void initGUI() {
        add(attackText, 0, 0);
        add(defenseText, 1, 0);
        add(powerText, 2, 0);
        refreshView();
    }

    @Override
    public void refreshView() {
        if (hasCard()) {
            CharacterCard card = (CharacterCard) this.card;
            attackText.setText("ATK " + card.getAttack());
            defenseText.setText("DEF " + card.getDefense());
            powerText.setText("PWR " + card.getPowerNeeded());
        }
    }

}
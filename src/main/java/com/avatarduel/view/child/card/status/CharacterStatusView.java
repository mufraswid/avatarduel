package com.avatarduel.view.child.card.status;

import com.avatarduel.Constants;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.CardView;

public class CharacterStatusView extends CardView {

    private DefaultText attackText, defenseText, powerText;

    public CharacterStatusView(CharacterCard card, boolean small) {
        super(card, "34,34,34", "100");
        attackText = new DefaultText();
        defenseText = new DefaultText();
        powerText = new DefaultText();
        if (small) {
            attackText.setSize(Constants.SMALL_FONT_SIZE);
            defenseText.setSize(Constants.SMALL_FONT_SIZE);
            powerText.setSize(Constants.SMALL_FONT_SIZE);
        }
        initGUI();
    }

    public CharacterStatusView(CharacterCard card) {
        this(card, false);
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
            int deltaAttack = card.getDeltaAttack();
            int deltaDefense = card.getDeltaDefense();
            attackText.setText("ATK " + card.getAttack() + (deltaAttack == 0 ? "" : deltaAttack));
            defenseText.setText("DEF " + card.getDefense() + (deltaDefense == 0 ? "" : deltaDefense));
            powerText.setText("PWR " + card.getPowerNeeded());
        }
    }

}
package com.avatarduel.view.child.card.status;

import com.avatarduel.Constants;
import com.avatarduel.controller.CardController;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.CardView;

public class CharacterStatusView extends CardView {

    public DefaultText attackText, defenseText, powerText;

    public CharacterStatusView(boolean small) {
        super("34,34,34", "100");
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

    public CharacterStatusView() {
        this(false);
    }

    @Override
    public void initGUI() {
        add(attackText, 0, 0);
        add(defenseText, 1, 0);
        add(powerText, 2, 0);
    }

    @Override
    public void renderCard(CardController card) {
        if (card.getCard() != null) {
            CharacterCard ccard = (CharacterCard) card.getCard();
            int deltaAttack = ccard.getDeltaAttack();
            int deltaDefense = ccard.getDeltaDefense();
            attackText.setText("ATK " + ccard.getAttack() + (deltaAttack == 0 ? "" : deltaAttack));
            defenseText.setText("DEF " + ccard.getDefense() + (deltaDefense == 0 ? "" : deltaDefense));
            powerText.setText("PWR " + ccard.getPowerNeeded());
        }
    }

}
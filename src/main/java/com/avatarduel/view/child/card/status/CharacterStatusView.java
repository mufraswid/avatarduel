package com.avatarduel.view.child.card.status;

import com.avatarduel.Constants;
import com.avatarduel.controller.card.CardController;
import com.avatarduel.controller.card.CharacterCardController;
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
    public void renderCard(CardController cc) {
        if (cc != null) {
            CharacterCardController ccc = (CharacterCardController) cc;
            int deltaAttack = ccc.getDeltaAttack();
            int deltaDefense = ccc.getDeltaDefense();
            CharacterCard card = (CharacterCard) cc.getCard();
            attackText.setText("ATK " + card.getAttack() + (deltaAttack == 0 ? "" : " + " + deltaAttack));
            defenseText.setText("DEF " + card.getDefense() + (deltaDefense == 0 ? "" : " + " + deltaDefense));
            powerText.setText("PWR " + card.getPowerNeeded());
        }
    }

}
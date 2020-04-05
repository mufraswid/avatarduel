package com.avatarduel.view.child.card.status;

import com.avatarduel.Constants;
import com.avatarduel.model.card.ActiveCharacterCard;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.CardView;

public class CharacterStatusView extends CardView {

    public CharacterStatusView(CharacterCard card, boolean small) {
        super("34,34,34", "100");
        DefaultText attackText = new DefaultText();
        DefaultText defenseText = new DefaultText();
        DefaultText powerText = new DefaultText();
        if (small) {
            attackText.setSize(Constants.SMALL_FONT_SIZE);
            defenseText.setSize(Constants.SMALL_FONT_SIZE);
            powerText.setSize(Constants.SMALL_FONT_SIZE);
            setHgap(0);
            setVgap(0);
        }
        String atk = "ATK " + card.getAttack();
        String def = "DEF " + card.getDefense();
        if (card instanceof ActiveCharacterCard) {
            ActiveCharacterCard acc = (ActiveCharacterCard) card;
            int dAtk = acc.getDeltaAttack();
            if (dAtk != 0) {
                atk += (dAtk > 0 ? "+" : "") + dAtk;
            }
            int dDef = acc.getDeltaDefense();
            if (dDef != 0) {
                def += (dDef > 0 ? "+" : "") + dDef;
            }
        }
        attackText.setText(atk);
        defenseText.setText(def);
        powerText.setText("PWR " + card.getPowerNeeded());
        add(attackText, 0, 0);
        add(defenseText, 1, 0);
        add(powerText, 2, 0);
    }

}
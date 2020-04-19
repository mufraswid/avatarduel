package com.avatarduel.view.child.card.status;

import com.avatarduel.model.card.ArenaCharacterCard;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.skill.AuraSkillCard;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.CardView;

/**
 * Status View for Cards that have attack, defense, and power
 */
public class StatusView extends CardView {

    private DefaultText attackText, defenseText, powerText;

    /**
     * Constructor
     *
     * @param small small flag
     */
    public StatusView(boolean small) {
        super("34,34,34", "100");
        attackText = new DefaultText();
        defenseText = new DefaultText();
        powerText = new DefaultText();
        if (small) {
            attackText.setSize(8);
            defenseText.setSize(8);
            powerText.setSize(8);
            setHgap(0);
            setVgap(0);
        }
        add(attackText, 0, 0);
        add(defenseText, 1, 0);
        add(powerText, 2, 0);
    }

    /**
     * @param card specified character card
     */
    public void render(CharacterCard card) {
        String atk = "ATK " + card.getAttack();
        String def = "DEF " + card.getDefense();
        if (card instanceof ArenaCharacterCard) {
            ArenaCharacterCard acc = (ArenaCharacterCard) card;
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
    }

    /**
     * @param card specified aura card
     */
    public void render(AuraSkillCard card) {
        int datk = card.getAttackAddition();
        int ddef = card.getDefenseAddition();
        attackText.setText("ATK " + (datk > 0 ? "+" : "") + datk);
        defenseText.setText("DEF " + (ddef > 0 ? "+" : "") + ddef);
        powerText.setText("PWR " + card.getPowerNeeded());
    }

}

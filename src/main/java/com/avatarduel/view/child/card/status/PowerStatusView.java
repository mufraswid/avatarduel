package com.avatarduel.view.child.card.status;

import com.avatarduel.model.card.PoweredCard;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.CardView;

/**
 * Status View for Cards that have power
 */
public class PowerStatusView extends CardView {

    private DefaultText powerText;

    /**
     * Constructor
     *
     * @param small small flag
     */
    public PowerStatusView(boolean small) {
        super("100", "100");
        powerText = new DefaultText();
        if (small) {
            powerText.setSize(8);
        }
        add(powerText, 0, 0);
    }

    /**
     * @param card specified power card
     */
    public void render(PoweredCard card) {
        powerText.setText("PWR " + card.getPowerNeeded());
    }

}

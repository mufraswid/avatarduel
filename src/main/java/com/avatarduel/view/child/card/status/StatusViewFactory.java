package com.avatarduel.view.child.card.status;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.PoweredCard;
import com.avatarduel.model.card.skill.AuraSkillCard;
import com.avatarduel.view.child.card.CardView;

/**
 * Status View Factory for creating StatusView based on the Card
 */
public final class StatusViewFactory {

    /**
     * Create a CardView according to type of card
     *
     * @param card specified card
     * @param small small flag
     * @return CardView
     */
    public static CardView createStatusView(Card card, boolean small) {
        if (card instanceof CharacterCard || card instanceof AuraSkillCard) {
            StatusView statusView = new StatusView(small);
            if (card instanceof CharacterCard) {
                statusView.render((CharacterCard) card);
            } else {
                statusView.render((AuraSkillCard) card);
            }
            return statusView;
        } else if (card instanceof PoweredCard) {
            PowerStatusView powerOnlyStatusView = new PowerStatusView(small);
            powerOnlyStatusView.render((PoweredCard) card);
            return powerOnlyStatusView;
        }
        return null;
    }

}

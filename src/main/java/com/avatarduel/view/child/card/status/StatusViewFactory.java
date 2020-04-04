package com.avatarduel.view.child.card.status;

import com.avatarduel.controller.CardController;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.skill.SkillCard;
import com.avatarduel.view.child.card.CardView;

public final class StatusViewFactory {
    public static CardView createStatusView(CardController card, boolean small) {
        if (card.getCard() instanceof SkillCard) {
            return new SkillStatusView(small);
        } else if (card.getCard() instanceof CharacterCard) {
            return new CharacterStatusView(small);
        }
        return null;
    }
}
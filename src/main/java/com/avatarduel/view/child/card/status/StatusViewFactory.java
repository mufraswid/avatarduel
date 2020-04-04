package com.avatarduel.view.child.card.status;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.skill.SkillCard;
import com.avatarduel.view.child.card.CardView;

public final class StatusViewFactory {
    public static CardView createStatusView(Card card) {
        if (card instanceof SkillCard) {
            return new SkillStatusView((SkillCard) card, true);
        } else if (card instanceof CharacterCard) {
            return new CharacterStatusView((CharacterCard) card, true);
        }
        return null;
    }
}
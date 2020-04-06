package com.avatarduel.view.child.card.status;

import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.skill.SkillCard;
import com.avatarduel.view.child.card.CardView;

public final class StatusViewFactory {
    public static CardView createStatusView(Card card, boolean small) {
        if (card instanceof SkillCard) {
            SkillStatusView skillStatusView = new SkillStatusView(small);
            skillStatusView.render((SkillCard) card);
            return skillStatusView;
        } else if (card instanceof CharacterCard) {
            CharacterStatusView characterStatusView = new CharacterStatusView(small);
            characterStatusView.render((CharacterCard) card);
            return characterStatusView;
        }
        return null;
    }
}
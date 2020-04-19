package com.avatarduel.view.child.card;

import com.avatarduel.controller.CardFieldDimension;
import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.IPlayer;
import com.avatarduel.view.GridView;
import com.avatarduel.view.ViewPosition;

/**
 * Display a field in the scene
 */
public class CardFieldView extends GridView {

    private CardRowFieldView characterRowFieldView, skillRowFieldView;

    /**
     * Constructor
     *
     * @param position field position
     * @param listener card event listener
     */
    public CardFieldView(CardFieldDimension cardFieldDimension, ViewPosition position, CardEventListener listener) {
        super("100", "50,50");
        characterRowFieldView = new CardRowFieldView(cardFieldDimension.getCharacterCardCount(), listener);
        skillRowFieldView = new CardRowFieldView(cardFieldDimension.getSkillCardCount(), listener);
        add(characterRowFieldView, 0, position == ViewPosition.BOTTOM ? 0 : 1);
        add(skillRowFieldView, 0, position == ViewPosition.BOTTOM ? 1 : 0);
    }

    /**
     * @param player specified player
     */
    public void render(IPlayer player) {
        characterRowFieldView.renderCards(player.getCharacterCards());
        skillRowFieldView.renderCards(player.getSkillCards());
    }

}

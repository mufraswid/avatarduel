package com.avatarduel.view.main;

import com.avatarduel.controller.CardFieldDimension;
import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.Card;
import com.avatarduel.view.GridView;
import com.avatarduel.view.ViewPosition;
import com.avatarduel.view.child.card.CardFieldView;
import com.avatarduel.view.child.card.HandCardFieldView;

/**
 * Organize the center panel of the scene
 */
public class CenterMainView extends GridView {

    private HandCardFieldView handCardFieldView1, handCardFieldView2;
    private CardFieldView cardFieldView1, cardFieldView2;

    /**
     * Constructor
     *
     * @param handCardListener  specified hand card listener
     * @param cardFieldListener specified card field listener
     */
    public CenterMainView(CardFieldDimension cardFieldDimension1, CardFieldDimension cardFieldDimension2,
            CardEventListener handCardListener, CardEventListener cardFieldListener) {
        super("100", "18,32,32,18");
        add(handCardFieldView2 = new HandCardFieldView(handCardListener), 0, 0);
        add(cardFieldView2 = new CardFieldView(cardFieldDimension2, ViewPosition.TOP, cardFieldListener), 0, 1);
        add(cardFieldView1 = new CardFieldView(cardFieldDimension1, ViewPosition.BOTTOM, cardFieldListener), 0, 2);
        add(handCardFieldView1 = new HandCardFieldView(handCardListener), 0, 3);
    }

    /**
     * @param player specified player
     * @param closed card state flag
     */
    public void renderHandCardView2(Player player, Card closed) {
        handCardFieldView2.render(player, closed);
    }

    /**
     * @param player specified player
     * @param closed card state flag
     */
    public void renderHandCardView1(Player player, Card closed) {
        handCardFieldView1.render(player, closed);
    }

    /**
     * @param player specified player
     */
    public void renderCardFieldView2(Player player) {
        cardFieldView2.render(player);
    }

    /**
     * @param player specified player
     */
    public void renderCardFieldView1(Player player) {
        cardFieldView1.render(player);
    }

}

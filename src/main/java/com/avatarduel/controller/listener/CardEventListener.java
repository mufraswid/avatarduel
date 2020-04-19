package com.avatarduel.controller.listener;

import com.avatarduel.model.card.Card;

/**
 * Define how Card event handled
 */
public interface CardEventListener {

    /**
     * Handle event when mouse hovered above card
     *
     * @param card the hovered card
     */
    public void onMouseEntered(Card card);

    /**
     * Handle event when mouse hovered out of card
     *
     * @param card the hovered card
     */
    public void onMouseExited(Card card);

    /**
     * Handle event when mouse right clicked on card
     *
     * @param card the clicked card
     */
    public void onMouseRightClicked(Card card);

    /**
     * Handle event when mouse left clicked on card
     *
     * @param card the clicked card
     */
    public void onMouseLeftClicked(Card card);

}

package com.avatarduel.model.card;

/**
 * Define the contract for a card to be putable
 */
public interface PutableCard {

    /**
     * @return new arena card
     */
    public Card createArenaCard();

}

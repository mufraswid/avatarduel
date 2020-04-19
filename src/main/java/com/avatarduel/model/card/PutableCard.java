package com.avatarduel.model.card;

/**
 * Define the contract for a card to be putable
 */
public interface PutableCard<T extends Card> {

    /**
     * @return new arena card
     */
    public T createArenaCard();

}

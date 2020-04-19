package com.avatarduel.model;

import java.util.List;

import com.avatarduel.controller.CardFieldDimension;
import com.avatarduel.model.card.ActivableCard;
import com.avatarduel.model.card.ArenaCharacterCard;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.PoweredCard;
import com.avatarduel.model.card.skill.PutableSkillCard;

public interface IPlayer {
    public int[] getCurrentElementValues();

    /**
     * @param count number of card to draw from deck
     */
    public void drawCard(int count);

    /**
     * Reset this player state
     */
    public void resetState();

    /**
     * Search for a specified card
     *
     * @param card specified card
     * @return true if found, false otherwise
     */
    public boolean hasCardOnField(ActivableCard card);

    /**
     * @return true if has any active character card, false otherwise
     */
    public boolean hasAnyArenaCharacterCard();

    /**
     * Draw 1 card from deck and add it to hand
     */
    public void drawCard();

    /**
     * Add max element value for this player
     *
     * @param el specified element
     */
    public void addMaxElement(Element el);

    /**
     * Add a specified value to a specified element of this player
     *
     * @param el    specified element
     * @param count number of element value to be added
     */
    public void addCurrentElement(Element el, int count);

    /**
     * Adding a list of card to deck
     *
     * @param cards specified list of card to add
     */
    public void addToDeck(List<ActivableCard> cards);

    /**
     * Get card field dimension for this player
     */

    public CardFieldDimension getCardFieldDimension();

    /**
     * @return this player name
     */
    public String getName();

    /**
     * @return this player hp
     */
    public int getHP();

    /**
     * @param element specified element
     * @return element value of this player
     */
    public int getCurrentElementValue(Element element);

    /**
     * @param element specified element
     * @return max element value of this player
     */
    public int getMaxElementValue(Element element);

    /**
     * @return this player's deck size
     */
    public int getCurrentDeckCount();

    /**
     * @return total number of card in deck
     */
    public int getTotalDeckCount();

    /**
     * @return list of cards in hand
     */
    public List<ActivableCard> getHandCards();

    /**
     * @return all character cards
     */
    public List<ArenaCharacterCard> getCharacterCards();

    /**
     * @return all skill cards
     */
    public List<PutableSkillCard> getSkillCards();

    /**
     * Remove a character card from specified index column
     *
     * @param idx index column
     */
    public void removeCharacterCard(int idx);

    /**
     * Remove a character card from a specified arena character card
     *
     * @param card specified arena character card
     */
    public boolean removeCharacterCard(ArenaCharacterCard card);

    /**
     * Remove a skill card from specified index column
     *
     * @param idx index column
     */
    public void removeSkillCard(int idx);

    /**
     * Remove a character card from a specified putable skill card
     *
     * @param card specified putable skill card
     */
    public boolean removeSkillCard(PutableSkillCard card);

    /**
     * Decrease HP by the amount of damage
     *
     * @param damage amount of damage dealt to this player
     */
    public void damage(int damage);

    public boolean hasOnHand(Card card);

    public boolean canSpendPower(PoweredCard card);

    public void spendPower(PoweredCard card);

    public boolean canPutCharacterCard();

    public boolean canPutSkillCard();

    public void putCharacterCard(ArenaCharacterCard card);

    public void putSkillCard(PutableSkillCard card);

    public boolean hasPutLandCard();

    public void setHasPutLandCard(boolean value);

    public void removeFromHand(ActivableCard card);

}
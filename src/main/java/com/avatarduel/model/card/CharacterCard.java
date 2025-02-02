package com.avatarduel.model.card;

import com.avatarduel.model.Element;
import com.avatarduel.model.IPlayer;

/**
 * Represents a character card
 */
public class CharacterCard extends ActivableCard implements PoweredCard, PutableCard<ArenaCharacterCard> {

    private int powerNeeded, attack, defense;

    /**
     * Constructor for character card
     *
     * @param imagePath   path to image resource
     * @param name        name for the card
     * @param description description for the card
     * @param elementType element type of land card
     * @param powerNeeded the needed power
     * @param attack      attack value
     * @param defense     defense value
     */
    public CharacterCard(String imagePath, String name, String description, Element elementType, int powerNeeded,
            int attack, int defense) {
        super(imagePath, name, description, elementType);
        setPowerNeeded(powerNeeded);
        setAttack(attack);
        setDefense(defense);
    }

    // #region setter
    /**
     * @param attack set attack value
     */
    private void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * @param defense set defense value
     */
    private void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * @param power set power needed value
     */
    private void setPowerNeeded(int power) {
        this.powerNeeded = power;
    }
    // #endregion

    // #region getter
    /**
     * @return this card type name
     */
    public String getCardTypeName() {
        return "Character Card";
    }

    /**
     * @return this card attack value
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * @return this card defense value
     */
    public int getDefense() {
        return this.defense;
    }

    /**
     * @return this card needed power value
     */
    public int getPowerNeeded() {
        return this.powerNeeded;
    }

    /**
     * @return make a new arena card from this card
     */
    @Override
    public ArenaCharacterCard createArenaCard() {
        return new ArenaCharacterCard(this);
    }
    // #endregion

    /**
     * @return a new copy of this character card
     */
    @Override
    public Card copy() {
        return new CharacterCard(imagePath, name, description, elementType, powerNeeded, attack, defense);
    }

    /** 
     * @param player
     * @return boolean
     */
    @Override
    public boolean canBePutOn(IPlayer player) {
        return super.canBePutOn(player) && player.canSpendPower(this) && player.canPutCharacterCard();
    }

    /** 
     * @param player
     */
    @Override
    public void putOn(IPlayer player) {
        super.putOn(player);
        player.spendPower(this);
        player.putCharacterCard(createArenaCard());
    }

}

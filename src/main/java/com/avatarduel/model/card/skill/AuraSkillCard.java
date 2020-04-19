package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;
import com.avatarduel.model.card.Card;

/**
 * Represents aura skill card
 */
public class AuraSkillCard extends PutableSkillCard {

    // AuraSkillCard attribute
    private int attackAddition, defenseAddition;

    /**
     * Constructor
     *
     * @param imagePath       path to image resource
     * @param name            name for the card
     * @param description     description for the card
     * @param elementType     element type of this card
     * @param powerNeeded     amount of power needed
     * @param attackAddition  amount of extra attack
     * @param defenseAddition amount of extra defense
     */
    public AuraSkillCard(String imagePath, String name, String description, Element elementType, int powerNeeded,
            int attackAddition, int defenseAddition) {
        super(imagePath, name, description, elementType, powerNeeded);
        setAttackAddition(attackAddition);
        setDefenseAddition(defenseAddition);
    }

    // #region setter
    /**
     * @param attackAddition amount of extra attack
     */
    private void setAttackAddition(int attackAddition) {
        this.attackAddition = attackAddition;
    }

    /**
     * @param defenseAddition amount of extra defense
     */
    private void setDefenseAddition(int defenseAddition) {
        this.defenseAddition = defenseAddition;
    }
    // #endregion

    // #region getter
    /**
     * @return this card type name
     */
    public String getCardTypeName() {
        return "Aura Skill Card";
    }

    /**
     * @return this card attack addition
     */
    public int getAttackAddition() {
        return this.attackAddition;
    }

    /**
     * @return this card defense addition
     */
    public int getDefenseAddition() {
        return this.defenseAddition;
    }

    // #endregion

    /**
     * @return this card
     */
    @Override
    public AuraSkillCard createArenaCard() {
        return this;
    }

    /**
     * @return a new copy of this card
     */
    @Override
    public Card copy() {
        return new AuraSkillCard(imagePath, name, description, elementType, powerNeeded, attackAddition,
                defenseAddition);
    }

}

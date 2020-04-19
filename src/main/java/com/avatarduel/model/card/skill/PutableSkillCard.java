package com.avatarduel.model.card.skill;

import com.avatarduel.model.Element;
import com.avatarduel.model.IPlayer;
import com.avatarduel.model.card.PutableCard;

public abstract class PutableSkillCard extends SkillCard implements PutableCard<PutableSkillCard> {

    public PutableSkillCard(String imagePath, String name, String description, Element elementType, int powerNeeded) {
        super(imagePath, name, description, elementType, powerNeeded);
    }

    @Override
    public boolean canBePutOn(IPlayer player) {
        return super.canBePutOn(player) && player.canSpendPower(this) && player.canPutSkillCard();
    }

    @Override
    public void putOn(IPlayer player) {
        super.putOn(player);
        player.putSkillCard(this);
    }

}
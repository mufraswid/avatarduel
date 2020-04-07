package com.avatarduel.controller;

import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.Player;
import com.avatarduel.model.card.ActiveCharacterCard;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.skill.DestroySkillCard;
import com.avatarduel.model.card.skill.SkillCard;

import javafx.scene.Cursor;

public class CardFieldEventListener implements CardEventListener {

    private GameController gameController;

    public CardFieldEventListener(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void onMouseEntered(Card card) {
        gameController.getRenderController().setLastTouchedCard(card);
        gameController.getRenderController().getScene().setCursor(Cursor.HAND);
    }

    @Override
    public void onMouseExited(Card card) {
        gameController.getRenderController().getScene().setCursor(Cursor.DEFAULT);
    }

    @Override
    public void onMouseRightClicked(Card card) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onMouseLeftClicked(Card card) {
        RenderController renderController = gameController.getRenderController();
        PlayerController playerController = gameController.getPlayerController();
        Player turn = playerController.getCurrentPlayerTurn();
        Card clicked = playerController.getClickedCard();
        if (clicked != null) {
            if (clicked instanceof SkillCard && card instanceof ActiveCharacterCard) {
                ActiveCharacterCard acc = (ActiveCharacterCard) card;
                if (clicked instanceof DestroySkillCard) {
                    playerController.removeCardFromField(acc);
                } else {
                    acc.addSkill((SkillCard) clicked);
                }
            }
        }
    }

}
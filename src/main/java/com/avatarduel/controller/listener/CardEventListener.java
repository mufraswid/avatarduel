package com.avatarduel.controller.listener;

import com.avatarduel.model.card.Card;

public interface CardEventListener {

    public void onMouseEntered(Card card);

    public void onMouseExited(Card card);

    public void onMouseRightClicked(Card card);

    public void onMouseLeftClicked(Card card);

}
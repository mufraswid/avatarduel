package com.avatarduel.model.card;

import com.avatarduel.model.Element;

public interface PoweredCard {
    public int getPowerNeeded();

    public Element getElementType();
}
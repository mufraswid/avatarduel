package com.avatarduel.model.card;

import com.avatarduel.model.Element;

/**
 * Kelas KartuLand: kelas yang memodelkan konsep kartu land, merupakan subclass dari kelas Kartu
 */
public class LandCard extends Card {
    // Konstruktor
    public LandCard(String imagePath, String name, String description, Element elementType) {
        super(imagePath, name, description, elementType);
    }

    @Override
    public String getDescription() {
        return "LAND CARD\n" + super.getDescription();
    }

    @Override
    public Card copy() {
        return new LandCard(imagePath, name, description, elementType);
    }
}

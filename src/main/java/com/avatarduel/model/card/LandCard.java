package com.avatarduel.model.card;

import com.avatarduel.model.Element;

/**
 * Kelas KartuLand: kelas yang memodelkan konsep kartu land, merupakan subclass dari kelas Kartu
 */
public class LandCard extends Card {
    // Konstruktor
    public LandCard(String imagePath, int id, String name, String description, Element elementType) {
        super(imagePath, id, name, description, elementType);
    }

    // Method untuk menambah power here
}

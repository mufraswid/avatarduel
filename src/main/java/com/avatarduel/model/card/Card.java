package com.avatarduel.model.card;

import com.avatarduel.model.Element;

/**
 * Kelas Abstrak Kartu: kelas yang memodelkan konsep kartu pada permainan, diturunkan menjadi berbagai jenis kartu: Karakter, Land, dan Skill
 */
public abstract class Card {
    // atribut
    private String name, description, imagePath;
    private Element elementType;

    public Card(String imagePath, String name, String description, Element elementType) {
        setImagePath(imagePath);
        setNama(name);
        setDesc(description);
        setElementType(elementType);
    }

    //#region setter
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setNama(String nama) {
        this.name = nama;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public void setElementType(Element elementType) {
        this.elementType = elementType;
    }
    //#endregion

    //#region getter
    public String getImagePath() {
        return this.imagePath;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Element getElementType() {
        return this.elementType;
    }
    //#endregion
}
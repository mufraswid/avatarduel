package com.avatarduel.model.card;

import com.avatarduel.model.Element;

/**
 * Kelas Abstrak Kartu: kelas yang memodelkan konsep kartu pada permainan, diturunkan menjadi berbagai jenis kartu: Karakter, Land, dan Skill
 */
public abstract class Card {
    // atribut
    private int id;
    private String name, description, imagePath;
    private Element elementType;

    public Card(String imagePath, int id, String name, String description, Element elementType) {
        setImagePath(imagePath);
        setNama(name);
        setDesc(description);
        setElementType(elementType);
    }

    //#region setter
    private void setId(int id) {
        this.id = id;
    }

    private void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private void setNama(String nama) {
        this.name = nama;
    }

    private void setDesc(String desc) {
        this.description = desc;
    }

    private void setElementType(Element elementType) {
        this.elementType = elementType;
    }
    //#endregion

    //#region getter
    public int getId() {
        return this.id;
    }

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

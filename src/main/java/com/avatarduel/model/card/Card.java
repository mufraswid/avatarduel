package com.avatarduel.model.card;

import com.avatarduel.model.Element;
import com.avatarduel.util.ResourceFinder;

/**
 * Kelas Abstrak Kartu: kelas yang memodelkan konsep kartu pada permainan, diturunkan menjadi berbagai jenis kartu: Karakter, Land, dan Skill
 */
public abstract class Card {
    // atribut
    private int id;
    private String name, description, imagePath;
    private Element elementType;
    private boolean isClosed;

    public Card(String imagePath, int id, String name, String description, Element elementType) {
        setImagePath(imagePath);
        setId(id);
        setNama(name);
        setDesc(description);
        setElementType(elementType);
        setClosed(false);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Card)) {
            return false;
        }
        Card card = (Card) o;
        return id == card.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    //#region setter
    public void setClosed(boolean closed) {
        this.isClosed = closed;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        // TODO delete later
        if (ResourceFinder.getURL(imagePath).contains("notfound.png")) {
            System.err.println("Path: " + imagePath + " not found!");
        }
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
    public boolean isClosed() {
        return isClosed;
    }

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

package com.avatarduel.model.card;

import java.util.UUID;

import com.avatarduel.model.Element;
import com.avatarduel.util.ResourceFinder;

/**
 * Kelas Abstrak Kartu: kelas yang memodelkan konsep kartu pada permainan, diturunkan menjadi berbagai jenis kartu: Karakter, Land, dan Skill
 */
public abstract class Card {
    // atribut
    private final UUID uuid;
    private String name, description, imagePath;
    private Element elementType;
    private boolean isClosed, isClicked;

    public Card(String imagePath, String name, String description, Element elementType) {
        setImagePath(imagePath);
        setNama(name);
        setDesc(description);
        setElementType(elementType);
        setClosed(false);
        uuid = UUID.randomUUID();
    }

    //#region setter
    public void setClosed(boolean closed) {
        this.isClosed = closed;
    }

    private void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public void setClicked(boolean clicked) {
        this.isClicked = clicked;
    }
    //#endregion

    //#region getter
    public boolean isClicked() {
        return isClicked;
    }

    public boolean isClosed() {
        return isClosed;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        if (uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!uuid.equals(other.uuid))
            return false;
        return true;
    }

}

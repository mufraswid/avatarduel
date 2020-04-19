package com.avatarduel.model.card;

import java.util.UUID;

import com.avatarduel.model.Element;
import com.avatarduel.util.ResourceFinder;

/**
 * Abstract class represents any card
 */
public abstract class Card {

    // Card attribute
    protected final UUID uuid;
    protected String name, description, imagePath;
    protected Element elementType;
    protected boolean isClosed, isClicked;

    /**
     * Constructor Card
     *
     * @param imagePath   path to image resource
     * @param name        name for the card
     * @param description description for the card
     * @param elementType element type of this card
     */
    public Card(String imagePath, String name, String description, Element elementType) {
        setImagePath(imagePath);
        setName(name);
        setDesc(description);
        setElementType(elementType);
        setClosed(false);
        uuid = UUID.randomUUID();
    }

    // #region setter
    /**
     * @param closed set card state
     */
    public void setClosed(boolean closed) {
        this.isClosed = closed;
    }

    /**
     * @param imagePath set image resource path
     */
    private void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        if (ResourceFinder.getURL(imagePath).contains("notfound.png")) {
            System.err.println("Path: " + imagePath + " not found!");
        }
    }

    /**
     * @param name set card name
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * @param desc set card description
     */
    private void setDesc(String desc) {
        this.description = desc;
    }

    /**
     * @param elementType set card element type
     */
    private void setElementType(Element elementType) {
        this.elementType = elementType;
    }

    /**
     * @param clicked set card click state
     */
    public void setClicked(boolean clicked) {
        this.isClicked = clicked;
    }
    // #endregion

    // #region getter
    /**
     * @return this card click state
     */
    public boolean isClicked() {
        return isClicked;
    }

    /**
     * @return this card closed state
     */
    public boolean isClosed() {
        return isClosed;
    }

    public UUID getId() {
        return uuid;
    }

    /**
     * @return this card image resource path
     */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * @return this card name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return this card description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return this card element type
     */
    public Element getElementType() {
        return this.elementType;
    }
    // #endregion

    /**
     * @return a copy of this card
     */
    public abstract Card copy();

    /**
     * @return this card hash code
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        return result;
    }

    /**
     * Compare this card to an object
     *
     * @param obj object to compare
     * @return true if obj is a card and have same uuid, false otherwise
     */
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

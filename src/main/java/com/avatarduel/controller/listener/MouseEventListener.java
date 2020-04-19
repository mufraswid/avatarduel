package com.avatarduel.controller.listener;

/**
 * Define how Mouse event handled
 */
public interface MouseEventListener {

    /**
     * Handle event when mouse hovered above the object
     */
    public void onMouseEntered();

    /**
     * Handle event when mouse hovered out of the object
     */
    public void onMouseExited();

    /**
     * Handle when right click event happened to object
     */
    public void onMouseRightClicked();

    /**
     * Handle when left click event happened to object
     */
    public void onMouseLeftClicked();

}

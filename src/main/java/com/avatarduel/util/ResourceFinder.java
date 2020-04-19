package com.avatarduel.util;

import java.net.URI;

import com.avatarduel.Main;

/**
 * This class used to find specific resource
 */
public final class ResourceFinder {

    private static final String NOT_FOUND_CARD_PATH = "card/image/notfound.png";

    /**
     * Constructor
     */
    private ResourceFinder() {
    }

    /**
     * @param path path for the resource
     * @return URI
     */
    public static URI getURI(String path) {
        try {
            return Main.class.getResource(path).toURI();
        } catch (Exception ex) {
            if (path.equals(NOT_FOUND_CARD_PATH)) {
                return null;
            }
            return getURI(NOT_FOUND_CARD_PATH);
        }
    }

    /**
     * @param path path for the resource
     * @return URL
     */
    public static String getURL(String path) {
        try {
            return Main.class.getResource(path).toURI().toURL().toString();
        } catch (Exception ex) {
            if (path.equals(NOT_FOUND_CARD_PATH)) {
                return null;
            }
            return getURL(NOT_FOUND_CARD_PATH);
        }
    }
}

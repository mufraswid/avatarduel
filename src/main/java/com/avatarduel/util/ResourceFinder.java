package com.avatarduel.util;

import java.net.URI;

import com.avatarduel.Main;

public final class ResourceFinder {

    private static final String NOT_FOUND_CARD_PATH = "card/image/notfound.png";

    private ResourceFinder() {
    }

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
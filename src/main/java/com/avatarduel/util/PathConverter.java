package com.avatarduel.util;

import java.net.URI;

import com.avatarduel.Main;

public final class PathConverter {

    private static final String NOT_FOUND_CARD_PATH = "card/image/notfound.png";

    public static URI convertPathToURI(String path) {
        try {
            return Main.class.getResource(path).toURI();
        } catch (Exception ex) {
            // System.err.println("Path: " + path + " not found!");
            if (path.equals(NOT_FOUND_CARD_PATH)) {
                return null;
            }
            return convertPathToURI(NOT_FOUND_CARD_PATH);
        }
    }

    public static String convertPathToURL(String path) {
        try {
            return Main.class.getResource(path).toURI().toURL().toString();
        } catch (Exception ex) {
            // System.err.println("Path: " + path + " not found!");
            if (path.equals(NOT_FOUND_CARD_PATH)) {
                return null;
            }
            return convertPathToURL(NOT_FOUND_CARD_PATH);
        }
    }
}
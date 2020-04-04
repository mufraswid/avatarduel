package com.avatarduel.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import com.avatarduel.Main;

public final class PathConverter {
    public static URI convertPathToURI(String path) {
        try {
            return Main.class.getResource(path).toURI();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String convertPathToURL(String path) {
        try {
            return Main.class.getResource(path).toURI().toURL().toString();
        } catch (URISyntaxException | MalformedURLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
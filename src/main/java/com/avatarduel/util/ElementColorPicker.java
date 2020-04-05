package com.avatarduel.util;

import com.avatarduel.model.Element;

import javafx.scene.paint.Color;

public class ElementColorPicker {
    public static Color getColor(Element el) {
        if (el != null) {
            switch (el) {
                case AIR:
                    return Color.YELLOW.darker();
                case EARTH:
                    return Color.GREEN.brighter();
                case WATER:
                    return Color.LIGHTBLUE;
                case FIRE:
                    return Color.RED.brighter();
            }
        }
        return Color.WHITE;
    }
}
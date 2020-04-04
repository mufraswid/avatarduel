package com.avatarduel.view;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DefaultText extends Text {

    public DefaultText() {
        this("");
    }

    public DefaultText(String text) {
        setFont(new Font("Arial", 16));
        setText(text);
    }

}
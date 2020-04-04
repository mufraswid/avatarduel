package com.avatarduel.view;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DefaultText extends Text {

    private boolean bold = false;
    private double size = 16;
    private String font = "Arial";

    public DefaultText() {
        this("");
    }

    public DefaultText(String text, boolean bold) {
        super(text);
        setBold(bold);
    }

    public DefaultText(String text) {
        super(text);
        setFont(new Font(font, size));
    }

    public void setBold(boolean bold) {
        setFont(Font.font(font, (this.bold = bold) ? FontWeight.BOLD : FontWeight.NORMAL, this.size));
    }

    public void setSize(double size) {
        setFont(Font.font(font, bold ? FontWeight.BOLD : FontWeight.NORMAL, this.size = size));
    }

}
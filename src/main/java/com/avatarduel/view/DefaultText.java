package com.avatarduel.view;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DefaultText extends AnchorPane {

    private boolean bold = false;
    private double size = 16;
    private String font = "Arial";
    private Text text;

    public DefaultText() {
        this("");
    }

    public DefaultText(String content, boolean bold) {
        this(content);
        setBold(bold);
    }

    public DefaultText(String content) {
        text = new Text(content);
        text.setFont(new Font(font, size));
        getChildren().add(text);
        setTopAnchor(text, 0d);
        setRightAnchor(text, 0d);
        setBottomAnchor(text, 0d);
        setLeftAnchor(text, 0d);
    }

    public Text getText() {
        return text;
    }

    public void setText(String content) {
        text.setText(content);
    }

    public void addBorder() {
        setBorder(BorderBuilder.createDefaultBorder());
    }

    public void removeBorder() {
        setBorder(null);
    }

    public void setBold(boolean bold) {
        text.setFont(Font.font(font, (this.bold = bold) ? FontWeight.BOLD : FontWeight.NORMAL, this.size));
    }

    public void setSize(double size) {
        text.setFont(Font.font(font, bold ? FontWeight.BOLD : FontWeight.NORMAL, this.size = size));
    }

}
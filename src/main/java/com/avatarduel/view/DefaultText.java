package com.avatarduel.view;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Represents a text
 */
public class DefaultText extends AnchorPane {

    private boolean bold = false;
    private double size = 16;
    private String font = "Arial";
    private Text text;

    /**
     * Construct DefaultText with empty content
     */
    public DefaultText() {
        this("");
    }

    /**
     * Construct bold DefaultText if bold true
     *
     * @param content content of text
     * @param bold    bold flag
     */
    public DefaultText(String content, boolean bold) {
        this(content);
        setBold(bold);
    }

    /**
     * Construct DefaultText with given content
     *
     * @param content content of text
     */
    public DefaultText(String content) {
        text = new Text(content);
        text.setFont(new Font(font, size));
        getChildren().add(text);
        setTopAnchor(text, 0d);
        setRightAnchor(text, 0d);
        setBottomAnchor(text, 0d);
        setLeftAnchor(text, 0d);
    }

    /**
     * @return this text
     */
    public Text getText() {
        return text;
    }

    /**
     * @param content content of text
     */
    public void setText(String content) {
        text.setText(content);
    }

    /**
     * Add border
     */
    public void addBorder() {
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
    }

    /**
     * Remove border
     */
    public void removeBorder() {
        setBorder(null);
    }

    /**
     * @param bold bold flag
     */
    public void setBold(boolean bold) {
        text.setFont(Font.font(font, (this.bold = bold) ? FontWeight.BOLD : FontWeight.NORMAL, this.size));
    }

    /**
     * @param size text size
     */
    public void setSize(double size) {
        text.setFont(Font.font(font, bold ? FontWeight.BOLD : FontWeight.NORMAL, this.size = size));
    }

}

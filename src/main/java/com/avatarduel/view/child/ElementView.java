package com.avatarduel.view.child;

import com.avatarduel.model.Element;
import com.avatarduel.model.IPlayer;
import com.avatarduel.util.ElementColorPicker;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

/**
 * Display each player's element status
 */
public class ElementView extends GridView {

    private DefaultText earthValue, fireValue, waterValue, airValue;

    /**
     * Constructor
     */
    public ElementView() {
        super("40,60", "25,25,25,25");

        DefaultText earthText = new DefaultText("Earth", true);
        earthText.getText().setFill(ElementColorPicker.getColor(Element.EARTH));
        DefaultText fireText = new DefaultText("Fire", true);
        fireText.getText().setFill(ElementColorPicker.getColor(Element.FIRE));
        DefaultText waterText = new DefaultText("Water", true);
        waterText.getText().setFill(Color.BLUE);
        DefaultText airText = new DefaultText("Air", true);
        airText.getText().setFill(ElementColorPicker.getColor(Element.AIR));

        getColumnConstraints().forEach(col -> col.setHalignment(HPos.LEFT));
        setPadding(new Insets(0, 0, 0, 10));
        addBorder();

        add(earthText, 0, 0);
        add(fireText, 0, 1);
        add(waterText, 0, 2);
        add(airText, 0, 3);

        add(earthValue = new DefaultText(), 1, 0);
        add(fireValue = new DefaultText(), 1, 1);
        add(waterValue = new DefaultText(), 1, 2);
        add(airValue = new DefaultText(), 1, 3);
    }

    /**
     * Get element value in string format
     *
     * @param player specified player
     * @param el     specified element
     * @return String with information about element value out of max element value
     */
    private String elementStringValue(IPlayer player, Element el) {
        return String.format(": %d / %d", player.getCurrentElementValue(el), player.getMaxElementValue(el));
    }

    /**
     * @param player specified player
     */
    public void render(IPlayer player) {
        earthValue.setText(elementStringValue(player, Element.EARTH));
        fireValue.setText(elementStringValue(player, Element.FIRE));
        waterValue.setText(elementStringValue(player, Element.WATER));
        airValue.setText(elementStringValue(player, Element.AIR));
    }

}

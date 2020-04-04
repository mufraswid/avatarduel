package com.avatarduel.view.child;

import com.avatarduel.Constants;
import com.avatarduel.controller.PlayerController;
import com.avatarduel.model.Element;
import com.avatarduel.util.ElementColorPicker;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public class ElementView extends GridView implements PlayerRenderer {

    public DefaultText earthText, fireText, waterText, airText, earthValue, fireValue, waterValue, airValue;

    public ElementView() {
        super("40,60", "25,25,25,25");

        earthText = new DefaultText("Earth", true);
        earthText.text.setFill(ElementColorPicker.getColor(Element.EARTH));
        fireText = new DefaultText("Fire", true);
        fireText.text.setFill(ElementColorPicker.getColor(Element.FIRE));
        waterText = new DefaultText("Water", true);
        waterText.text.setFill(Color.BLUE);
        airText = new DefaultText("Air", true);
        airText.text.setFill(ElementColorPicker.getColor(Element.AIR));

        earthValue = new DefaultText();
        fireValue = new DefaultText();
        waterValue = new DefaultText();
        airValue = new DefaultText();

        initGUI();
    }

    @Override
    public void initGUI() {
        getColumnConstraints().forEach(col -> col.setHalignment(HPos.LEFT));
        setPadding(new Insets(0, 0, 0, Constants.GAP));
        addBorder();

        add(earthText, 0, 0);
        add(fireText, 0, 1);
        add(waterText, 0, 2);
        add(airText, 0, 3);

        add(earthValue, 1, 0);
        add(fireValue, 1, 1);
        add(waterValue, 1, 2);
        add(airValue, 1, 3);
    }

    @Override
    public void renderPlayer(PlayerController player) {
        earthValue.setText(elementStringValue(player, Element.EARTH));
        fireValue.setText(elementStringValue(player, Element.FIRE));
        waterValue.setText(elementStringValue(player, Element.WATER));
        airValue.setText(elementStringValue(player, Element.AIR));
    }

    private String elementStringValue(PlayerController player, Element el) {
        return String.format(": %d / %d", player.getCurrentElementValue(el), player.getMaxElementValue(el));
    }

}
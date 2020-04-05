package com.avatarduel.view.child;

import com.avatarduel.Constants;
import com.avatarduel.model.Element;
import com.avatarduel.model.Player;
import com.avatarduel.util.ElementColorPicker;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public class ElementView extends GridView {

    public ElementView(Player player) {
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
        setPadding(new Insets(0, 0, 0, Constants.GAP));
        addBorder();

        add(earthText, 0, 0);
        add(fireText, 0, 1);
        add(waterText, 0, 2);
        add(airText, 0, 3);

        add(new DefaultText(elementStringValue(player, Element.EARTH)), 1, 0);
        add(new DefaultText(elementStringValue(player, Element.FIRE)), 1, 1);
        add(new DefaultText(elementStringValue(player, Element.WATER)), 1, 2);
        add(new DefaultText(elementStringValue(player, Element.AIR)), 1, 3);
    }

    private String elementStringValue(Player player, Element el) {
        return String.format(": %d / %d", player.getCurrentElementValue(el), player.getMaxElementValue(el));
    }

}
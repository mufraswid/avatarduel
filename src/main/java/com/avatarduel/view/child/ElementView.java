package com.avatarduel.view.child;

import com.avatarduel.model.Element;
import com.avatarduel.model.Player;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;
import com.avatarduel.view.RefreshableView;

public class ElementView extends GridView implements RefreshableView {

    private DefaultText earthText, fireText, waterText, airText, earthValue, fireValue, waterValue, airValue;
    private Player player;

    public ElementView(Player player) {
        super("40,60", "25,25,25,25");

        this.player = player;

        earthText = new DefaultText("Earth");
        fireText = new DefaultText("Fire");
        waterText = new DefaultText("Water");
        airText = new DefaultText("Air");

        earthValue = new DefaultText();
        fireValue = new DefaultText();
        waterValue = new DefaultText();
        airValue = new DefaultText();

        refreshView();
    }

    private String elementStringValue(Element el) {
        return String.format("%d / %d", player.getCurrentElementValue(el), player.getMaxElementValue(el));
    }

    @Override
    public void initGUI() {
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
    public void refreshView() {
        earthValue.setText(elementStringValue(Element.EARTH));
        fireValue.setText(elementStringValue(Element.FIRE));
        waterValue.setText(elementStringValue(Element.WATER));
        airValue.setText(elementStringValue(Element.AIR));
    }

}
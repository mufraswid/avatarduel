package com.avatarduel.view.child.card;

import com.avatarduel.model.card.Card;
import com.avatarduel.util.ElementColorPicker;
import com.avatarduel.util.PathConverter;
import com.avatarduel.view.DefaultText;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

public class BigCardView extends CardView {

    public BigCardView(Card card) {
        super("100", "10,50,40");
        DefaultText nameText = new DefaultText("", true);
        ImageView imageView = new ImageView();
        DescriptionCardView descCardView = new DescriptionCardView(card);
        setBackground(new Background(new BackgroundFill(ElementColorPicker.getColor(card.getElementType()),
                CornerRadii.EMPTY, Insets.EMPTY)));
        nameText.setText(card.getName());
        imageView.setImage(new Image(PathConverter.convertPathToURL(card.getImagePath())));
        add(nameText, 0, 0);
        add(imageView, 0, 1);
        add(descCardView, 0, 2);
    }

}
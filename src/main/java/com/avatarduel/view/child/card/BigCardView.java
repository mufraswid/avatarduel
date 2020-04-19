package com.avatarduel.view.child.card;

import com.avatarduel.model.card.Card;
import com.avatarduel.util.ElementColorPicker;
import com.avatarduel.util.ResourceFinder;
import com.avatarduel.view.DefaultText;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

/**
 * Display a big card
 */
public class BigCardView extends CardView {

    private DefaultText nameText;
    private ImageView imageView;
    private DescriptionCardView descCardView;

    /**
     * Constructor
     */
    public BigCardView() {
        super("100", "10,50,40");
        nameText = new DefaultText("", true);
        imageView = new ImageView();
        descCardView = new DescriptionCardView();
        add(nameText, 0, 0);
        add(imageView, 0, 1);
        add(descCardView, 0, 2);
    }

    /**
     * @param card specified card
     */
    public void render(Card card) {
        setBackground(new Background(new BackgroundFill(ElementColorPicker.getColor(card.getElementType()),
                CornerRadii.EMPTY, Insets.EMPTY)));
        nameText.setText(card.getName());
        imageView.setImage(new Image(ResourceFinder.getURL(card.getImagePath())));
        descCardView.renderCard(card);
    }

}

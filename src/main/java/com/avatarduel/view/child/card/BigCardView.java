package com.avatarduel.view.child.card;

import com.avatarduel.model.card.Card;
import com.avatarduel.util.PathConverter;
import com.avatarduel.view.DefaultText;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BigCardView extends CardView {

    private DefaultText nameText;
    private ImageView imageView;
    private DescriptionCardView descCardView;

    public BigCardView(Card card) {
        super(card, "100", "10,50,40");
        this.nameText = new DefaultText();
        this.imageView = new ImageView();
        this.descCardView = new DescriptionCardView(card);
        initGUI();
    }

    @Override
    public void initGUI() {
        add(nameText, 0, 0);
        add(imageView, 0, 1);
        add(descCardView, 0, 2);
        refreshView();
    }

    @Override
    public void setCard(Card card) {
        if (!hasCard() || !getCard().equals(card)) {
            descCardView.setCard(card);
        }
        super.setCard(card);
    }

    @Override
    public void refreshView() {
        if (hasCard()) {
            nameText.setText(card.getName());
            imageView.setImage(new Image(PathConverter.convertPathToURL(card.getImagePath())));
        }
        super.refreshView();
    }

}
package com.avatarduel.view.child.card;

import com.avatarduel.Constants;
import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.card.Card;
import com.avatarduel.util.ElementColorPicker;
import com.avatarduel.util.ResourceFinder;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.status.StatusViewFactory;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

public class SmallCardView extends CardView {

    private CardEventListener listener;
    private DefaultText nameText;
    private ImageView imageView;

    public SmallCardView(CardEventListener listener) {
        super("100", "10,80,10");
        this.listener = listener;
        nameText = new DefaultText();
        imageView = new ImageView();
        setVgap(0);
        setHgap(0);

        nameText.setSize(Constants.SMALL_FONT_SIZE);
        imageView.setPreserveRatio(true);
        // imageView.fitWidthProperty().bind(widthProperty().subtract(Constants.GAP));
        imageView.setFitWidth(80);
    }

    public void render(Card card) {
        nameText.setText(card.getName());
        imageView.setImage(new Image(ResourceFinder.getURL(card.getImagePath())));
        CardView statusCardView = StatusViewFactory.createStatusView(card, true);
        setBackground(new Background(new BackgroundFill(ElementColorPicker.getColor(card.getElementType()),
                CornerRadii.EMPTY, Insets.EMPTY)));
        getChildren().clear();
        add(nameText, 0, 0);
        add(imageView, 0, 1);
        if (statusCardView != null) {
            add(statusCardView, 0, 2);
        }
        setOnMouseEntered(e -> listener.onMouseEntered(card));
        setOnMouseExited(e -> listener.onMouseExited(card));
        setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                listener.onMouseLeftClicked(card);
            } else {
                listener.onMouseRightClicked(card);
            }
        });
    }

}
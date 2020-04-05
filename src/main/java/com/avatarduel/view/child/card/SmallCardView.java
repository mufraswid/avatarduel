package com.avatarduel.view.child.card;

import com.avatarduel.Constants;
import com.avatarduel.controller.GameController;
import com.avatarduel.controller.listener.CardEventListener;
import com.avatarduel.model.card.Card;
import com.avatarduel.util.ElementColorPicker;
import com.avatarduel.util.PathConverter;
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

    public SmallCardView(Card card, CardEventListener listener) {
        super("100", "10,80,10");
        DefaultText nameText = new DefaultText();
        ImageView imageView = new ImageView();
        setVgap(0);
        setHgap(0);

        nameText.setSize(Constants.SMALL_FONT_SIZE);
        imageView.setPreserveRatio(true);
        // imageView.fitWidthProperty().bind(widthProperty().subtract(Constants.GAP));
        imageView.setFitWidth(80);

        nameText.setText(card.getName());
        imageView.setImage(new Image(PathConverter.convertPathToURL(card.getImagePath())));
        CardView statusCardView = StatusViewFactory.createStatusView(card, true);
        setBackground(new Background(new BackgroundFill(ElementColorPicker.getColor(card.getElementType()),
                CornerRadii.EMPTY, Insets.EMPTY)));
        getChildren().clear();
        if (card == GameController.getInstance().getClosedCard()) {
            add(imageView, 0, 0, 1, 3);
        } else {
            add(nameText, 0, 0);
            add(imageView, 0, 1);
            if (statusCardView != null) {
                add(statusCardView, 0, 2);
            }
        }
        setOnMouseEntered(e -> listener.onMouseEntered(card));
        setOnMouseExited(e -> listener.onMouseExited(card));
        // setOnMouseEntered(e -> {
        //     GameController.getInstance().setTouchedCard(cc);
        //     GameController.getInstance().getScene().setCursor(Cursor.HAND);
        // });
        // setOnMouseExited(e -> {
        //     GameController.getInstance().getScene().setCursor(Cursor.DEFAULT);
        // });
        setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                listener.onMouseLeftClicked(card);
            } else {
                listener.onMouseRightClicked(card);
            }
        });
    }

}
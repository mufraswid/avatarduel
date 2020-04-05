package com.avatarduel.view.child.card;

import com.avatarduel.Constants;
import com.avatarduel.controller.GameController;
import com.avatarduel.controller.card.CardController;
import com.avatarduel.model.card.Card;
import com.avatarduel.util.ElementColorPicker;
import com.avatarduel.util.PathConverter;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.status.StatusViewFactory;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

public class SmallCardView extends CardView {

    private DefaultText nameText;
    private ImageView imageView;
    private CardView statusCardView;

    public SmallCardView() {
        super("100", "10,80,10");
        nameText = new DefaultText();
        imageView = new ImageView();
        setVgap(0);
        setHgap(0);
        initGUI();
    }

    @Override
    public void initGUI() {
        nameText.setSize(Constants.SMALL_FONT_SIZE);
        imageView.setPreserveRatio(true);
        // imageView.fitWidthProperty().bind(widthProperty().subtract(Constants.GAP));
        imageView.setFitWidth(80);
    }

    @Override
    public void renderCard(CardController cc) {
        if (cc != null) {
            setOnMouseEntered(e -> {
                GameController.getInstance().setTouchedCard(cc);
                GameController.getInstance().getScene().setCursor(Cursor.HAND);
            });
            setOnMouseExited(e -> {
                GameController.getInstance().getScene().setCursor(Cursor.DEFAULT);
            });
            Card card = cc.getCard();
            nameText.setText(card.getName());
            imageView.setImage(new Image(PathConverter.convertPathToURL(card.getImagePath())));
            statusCardView = StatusViewFactory.createStatusView(cc, true);
            setBackground(new Background(new BackgroundFill(ElementColorPicker.getColor(card.getElementType()),
                    CornerRadii.EMPTY, Insets.EMPTY)));
            getChildren().clear();
            if (cc == GameController.getInstance().getClosedCard()) {
                add(imageView, 0, 0, 1, 3);
            } else {
                add(nameText, 0, 0);
                add(imageView, 0, 1);
                if (statusCardView != null) {
                    add(statusCardView, 0, 2);
                    statusCardView.renderCard(cc);
                }
            }
        }
    }

}
package com.avatarduel.view.child.card;

import com.avatarduel.Constants;
import com.avatarduel.controller.Game;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.CharacterCard;
import com.avatarduel.model.card.skill.SkillCard;
import com.avatarduel.util.ElementColorPicker;
import com.avatarduel.util.PathConverter;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.child.card.status.CharacterStatusView;
import com.avatarduel.view.child.card.status.SkillStatusView;

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

    public SmallCardView(Card card) {
        super(card, "100", "10,90,10");
        nameText = new DefaultText();
        imageView = new ImageView();
        setClosed(true);
        initGUI();
    }

    @Override
    public void initGUI() {
        hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (!hasCard()) {
                return;
            }
            if (newValue) {
                Game.getInstance().getMainView().getBigCardView().setCard(getCard());
                Game.getInstance().getScene().setCursor(Cursor.HAND);
            } else {
                Game.getInstance().getScene().setCursor(Cursor.DEFAULT);
            }
        });
        nameText.setSize(Constants.SMALL_FONT_SIZE);
        imageView.setPreserveRatio(true);
        // imageView.fitWidthProperty().bind(widthProperty().subtract(Constants.GAP));
        imageView.setFitWidth(100);
        refreshView();
    }

    @Override
    public void refreshView() {
        if (hasCard()) {
            nameText.setText(card.getName());
            imageView.setImage(new Image(PathConverter.convertPathToURL(card.getImagePath())));
            if (card instanceof SkillCard) {
                statusCardView = new SkillStatusView((SkillCard) card, true);
            } else if (card instanceof CharacterCard) {
                statusCardView = new CharacterStatusView((CharacterCard) card, true);
            }
            setBackground(new Background(new BackgroundFill(ElementColorPicker.getColor(card.getElementType()),
                    CornerRadii.EMPTY, Insets.EMPTY)));
            getChildren().clear();
            add(nameText, 0, 0);
            add(imageView, 0, 1);
            add(statusCardView, 0, 2);
        }
    }

}
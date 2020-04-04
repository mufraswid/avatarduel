package com.avatarduel.view.child.card;

import java.util.List;
import java.util.stream.Collectors;

import com.avatarduel.Constants;
import com.avatarduel.controller.PlayerController;
import com.avatarduel.view.BorderBuilder;
import com.avatarduel.view.View;
import com.avatarduel.view.child.PlayerRenderer;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class HandCardFieldView extends ScrollPane implements View, PlayerRenderer {

    private List<SmallCardView> smallCardViews;
    private HBox hbox;

    public HandCardFieldView() {
        hbox = new HBox();
        initGUI();
    }

    public void addBorder() {
        setBorder(BorderBuilder.createDefaultBorder());
    }

    @Override
    public void initGUI() {
        setContent(hbox);
        addBorder();
        setPadding(new Insets(Constants.GAP, Constants.GAP, Constants.GAP, Constants.GAP));
    }

    @Override
    public void renderPlayer(PlayerController player) {
        hbox.getChildren().clear();
        this.smallCardViews = player.getHandCards().stream().map(card -> {
            SmallCardView smallCardView = new SmallCardView();
            smallCardView.renderCard(player.isPlaying() ? card : null);
            return smallCardView;
        }).collect(Collectors.toList());
        for (SmallCardView card : smallCardViews) {
            hbox.getChildren().add(card);
        }
    }

}
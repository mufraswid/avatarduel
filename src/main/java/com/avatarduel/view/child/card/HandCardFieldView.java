package com.avatarduel.view.child.card;

import java.util.List;
import java.util.stream.Collectors;

import com.avatarduel.model.Player;

public class HandCardFieldView extends CardView {

    private Player player;
    private List<SmallCardView> smallCardViews;

    public HandCardFieldView(Player player) {
        super(null, "100", "100");
        this.player = player;
        refreshView();
        initGUI();
    }

    @Override
    public void setClosed(boolean closed) {
        super.setClosed(closed);
        for (ClosableCard card : smallCardViews) {
            card.setClosed(closed);
        }
    }

    @Override
    public void initGUI() {
        int i = 0;
        for (SmallCardView card : smallCardViews) {
            add(card, i++, 0);
        }
    }

    @Override
    public void refreshView() {
        getChildren().clear();
        this.smallCardViews = player.getCardList().stream().map(card -> new SmallCardView(card))
                .collect(Collectors.toList());
        initGUI();
    }

}
package com.avatarduel.view.child.card;

import java.util.List;
import java.util.stream.Collectors;

import com.avatarduel.model.Player;
import com.avatarduel.view.GridView;
import com.avatarduel.view.RefreshableView;

public class HandCardFieldView extends GridView implements ClosableCard, RefreshableView {

    private Player player;
    private List<SmallCardView> smallCardViews;

    public HandCardFieldView(Player player) {
        super("100", "100");
        this.player = player;
        refreshView();
    }

    @Override
    public void setClosed(boolean closed) {
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
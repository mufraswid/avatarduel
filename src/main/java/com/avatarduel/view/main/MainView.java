package com.avatarduel.view.main;

import com.avatarduel.Constants;
import com.avatarduel.view.GridView;
import com.avatarduel.view.child.card.BigCardView;

import javafx.geometry.Insets;

public class MainView extends GridView {

    public LeftMainView leftMainView;
    public CenterMainView centerMainView;
    public RightMainView rightMainView;

    public MainView() {
        super("20,70,10", "100");
        leftMainView = new LeftMainView();
        centerMainView = new CenterMainView();
        rightMainView = new RightMainView();
        setPadding(new Insets(Constants.GAP, Constants.GAP, Constants.GAP, Constants.GAP));
        initGUI();
    }

    public BigCardView getBigCardView() {
        return leftMainView.getBigCardView();
    }

    @Override
    public void initGUI() {
        add(leftMainView, 0, 0);
        add(centerMainView, 1, 0);
        add(rightMainView, 2, 0);
    }

}
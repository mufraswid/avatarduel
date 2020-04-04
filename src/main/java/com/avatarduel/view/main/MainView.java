package com.avatarduel.view.main;

import com.avatarduel.view.GridView;

public class MainView extends GridView {

    private LeftMainView leftMainView;
    private CenterMainView centerMainView;
    private RightMainView rightMainView;

    public MainView() {
        super("20,70,10", "100");

        leftMainView = new LeftMainView();
        centerMainView = new CenterMainView();
        rightMainView = new RightMainView();
    }

    @Override
    public void initGUI() {
        add(leftMainView, 0, 0);
        add(centerMainView, 1, 0);
        add(rightMainView, 2, 0);
    }

}
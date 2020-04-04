package com.avatarduel.view.main;

import com.avatarduel.view.GridView;
import com.avatarduel.view.child.ElementView;
import com.avatarduel.view.child.PhaseView;

public class RightMainView extends GridView {

    public PhaseView phaseView;
    public ElementView elementView1, elementView2;

    public RightMainView() {
        super("100", "15,20,30,20,15");
        phaseView = new PhaseView();
        elementView1 = new ElementView();
        elementView2 = new ElementView();
        initGUI();
    }

    @Override
    public void initGUI() {
        add(elementView2, 0, 1);
        add(phaseView, 0, 2);
        add(elementView1, 0, 3);
    }

}
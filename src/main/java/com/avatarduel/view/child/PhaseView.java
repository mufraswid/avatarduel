package com.avatarduel.view.child;

import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;

public class PhaseView extends GridView {

    private DefaultText drawText, main1Text, battleText, main2Text, endText;

    public PhaseView() {
        super("100", "20,20,20,20,20");
        drawText = new DefaultText("DRAW");
        main1Text = new DefaultText("MAIN 1");
        battleText = new DefaultText("BATTLE");
        main2Text = new DefaultText("MAIN 2");
        endText = new DefaultText("END");
    }

    @Override
    public void initGUI() {
        add(drawText, 0, 0);
        add(main1Text, 0, 1);
        add(battleText, 0, 2);
        add(main2Text, 0, 3);
        add(endText, 0, 4);
    }

}
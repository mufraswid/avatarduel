package com.avatarduel.view.child;

import com.avatarduel.model.Phase;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;

public class PhaseView extends GridView {

    public DefaultText[] texts;

    public PhaseView() {
        super("100", "20,20,20,20,20");
        texts = new DefaultText[Phase.values().length];
        for (int i = 0; i < Phase.values().length; ++i) {
            texts[i] = new DefaultText(Phase.values()[i].toString());
        }
        initGUI();
    }

    public void setPhase(Phase phase) {
        for (int i = 0; i < Phase.values().length; ++i) {
            if (i == phase.ordinal()) {
                texts[i].addBorder();
            } else {
                texts[i].removeBorder();
            }
        }
    }

    @Override
    public void initGUI() {
        addBorder();
        for (int i = 0; i < Phase.values().length; ++i) {
            add(texts[i], 0, i);
        }
    }

}
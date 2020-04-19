package com.avatarduel.view.child;

import com.avatarduel.controller.listener.MouseEventListener;
import com.avatarduel.model.Phase;
import com.avatarduel.view.DefaultText;
import com.avatarduel.view.GridView;

import javafx.scene.input.MouseButton;

/**
 * Display phases
 */
public class PhaseView extends GridView {

    private DefaultText[] texts;

    /**
     * Constructor
     *
     * @param listener mouse event listener
     */
    public PhaseView(MouseEventListener listener) {
        super("100", "20,20,20,20,20");
        texts = new DefaultText[Phase.values().length];
        for (int i = 0; i < Phase.values().length; ++i) {
            texts[i] = new DefaultText(Phase.values()[i].toString());
        }
        addBorder();
        for (int i = 0; i < Phase.values().length; ++i) {
            add(texts[i], 0, i);
        }
        setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                listener.onMouseLeftClicked();
            } else {
                listener.onMouseRightClicked();
            }
        });
        setOnMouseEntered(e -> listener.onMouseEntered());
        setOnMouseExited(e -> listener.onMouseExited());
    }


    /**
     * @param phase specified phase
     */
    public void render(Phase phase) {
        for (int i = 0; i < Phase.values().length; ++i) {
            if (i == phase.ordinal()) {
                texts[i].addBorder();
            } else {
                texts[i].removeBorder();
            }
        }
    }

}

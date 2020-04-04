package com.avatarduel.view;

import java.util.Arrays;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public abstract class GridView extends GridPane implements View {

    private static final String SEPARATOR = ",";

    public GridView(double[] colPercentages, double[] rowPercentages) {
        getColumnConstraints().setAll((ColumnConstraints[]) Arrays.stream(colPercentages).mapToObj(d -> {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(d);
            return col;
        }).toArray());
        getRowConstraints().setAll((RowConstraints[]) Arrays.stream(rowPercentages).mapToObj(d -> {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(d);
            return row;
        }).toArray());
        initGUI();
    }

    public GridView(String colPercentages, String rowPercentages) {
        this(Arrays.stream(colPercentages.split(SEPARATOR)).mapToDouble(Double::parseDouble).toArray(),
                Arrays.stream(rowPercentages.split(SEPARATOR)).mapToDouble(Double::parseDouble).toArray());
    }

}
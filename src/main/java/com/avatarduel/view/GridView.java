package com.avatarduel.view;

import java.util.Arrays;

import com.avatarduel.Constants;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public abstract class GridView extends GridPane implements View {

    private static final String SEPARATOR = ",";

    public GridView(double[] colPercentages, double[] rowPercentages) {
        Object[] arr = Arrays.stream(colPercentages).mapToObj(d -> {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(d);
            // col.setPrefWidth(d * ((Region) getParent()).getPrefWidth() / 100d);
            // col.prefWidthProperty().bind(((Region) getParent()).prefWidthProperty().multiply(d).divide(100d));
            col.setHgrow(Priority.ALWAYS);
            col.setHalignment(HPos.CENTER);
            return col;
        }).toArray();
        getColumnConstraints().setAll(Arrays.copyOf(arr, arr.length, ColumnConstraints[].class));

        arr = Arrays.stream(rowPercentages).mapToObj(d -> {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(d);
            // row.setPrefHeight(d * ((Region) getParent()).getPrefHeight() / 100d);
            // row.prefHeightProperty().bind(((Region) getParent()).prefHeightProperty().multiply(d).divide(100d));
            row.setVgrow(Priority.ALWAYS);
            row.setValignment(VPos.CENTER);
            return row;
        }).toArray();
        getRowConstraints().setAll(Arrays.copyOf(arr, arr.length, RowConstraints[].class));

        setHgap(Constants.GAP);
        setVgap(Constants.GAP);
    }

    public void addBorder() {
        setBorder(BorderBuilder.createDefaultBorder());
    }

    public void removeBorder() {
        setBorder(null);
    }

    public GridView(String colPercentages, String rowPercentages) {
        this(Arrays.stream(colPercentages.split(SEPARATOR)).mapToDouble(Double::parseDouble).toArray(),
                Arrays.stream(rowPercentages.split(SEPARATOR)).mapToDouble(Double::parseDouble).toArray());
    }

}
package com.avatarduel.view;

import java.util.Arrays;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

public abstract class GridView extends GridPane {

    private static final String SEPARATOR = ",";

    public GridView(double[] colPercentages, double[] rowPercentages) {
        Object[] arr = Arrays.stream(colPercentages).mapToObj(d -> {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(d);
            col.setHgrow(Priority.SOMETIMES);
            col.setHalignment(HPos.CENTER);
            return col;
        }).toArray();
        getColumnConstraints().setAll(Arrays.copyOf(arr, arr.length, ColumnConstraints[].class));

        arr = Arrays.stream(rowPercentages).mapToObj(d -> {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(d);
            row.setVgrow(Priority.SOMETIMES);
            row.setValignment(VPos.CENTER);
            return row;
        }).toArray();
        getRowConstraints().setAll(Arrays.copyOf(arr, arr.length, RowConstraints[].class));

        // setHgap(Constants.GAP);
        // setVgap(Constants.GAP);
    }

    public void addBorder() {
        addBorder(Color.BLACK, BorderStrokeStyle.SOLID, 1);
    }

    public void addBorder(Color color, BorderStrokeStyle style, int widths) {
        setBorder(new Border(new BorderStroke(color, style, null, new BorderWidths(widths))));
    }

    public void removeBorder() {
        setBorder(null);
    }

    public GridView(String colPercentages, String rowPercentages) {
        this(Arrays.stream(colPercentages.split(SEPARATOR)).mapToDouble(Double::parseDouble).toArray(),
                Arrays.stream(rowPercentages.split(SEPARATOR)).mapToDouble(Double::parseDouble).toArray());
    }

}
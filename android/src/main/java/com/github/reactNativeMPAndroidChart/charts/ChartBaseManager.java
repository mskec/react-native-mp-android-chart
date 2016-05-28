package com.github.reactNativeMPAndroidChart.charts;

import android.graphics.Color;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.animation.Easing.EasingOption;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.reactNativeMPAndroidChart.utils.BridgeUtils;

import java.util.ArrayList;

public abstract class ChartBaseManager<T extends Chart, U extends Entry> extends SimpleViewManager {


    /**
     * More details about legend customization: https://github.com/PhilJay/MPAndroidChart/wiki/Legend
     */
    @ReactProp(name = "legend")
    public void setLegend(T chart, ReadableMap propMap) {
        Legend legend = chart.getLegend();

        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "enabled")) {
            legend.setEnabled(propMap.getBoolean("enabled"));
        }

        // Styling
        if (BridgeUtils.validate(propMap, ReadableType.String, "textColor")) {
            legend.setTextColor(Color.parseColor(propMap.getString("textColor")));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "textSize")) {
            legend.setTextSize((float) propMap.getDouble("textSize"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "fontFamily") ||
                BridgeUtils.validate(propMap, ReadableType.Number, "fontStyle")) {
            legend.setTypeface(BridgeUtils.parseTypeface(propMap, "fontStyle", "fontFamily"));
        }

        // Wrapping / clipping avoidance
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "wordWrapEnabled")) {
            legend.setWordWrapEnabled(propMap.getBoolean("wordWrapEnabled"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "maxSizePercent")) {
            legend.setMaxSizePercent((float) propMap.getDouble("maxSizePercent"));
        }

        // Customizing
        if (BridgeUtils.validate(propMap, ReadableType.String, "position")) {
            legend.setPosition(LegendPosition.valueOf(propMap.getString("position").toUpperCase()));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "form")) {
            legend.setForm(LegendForm.valueOf(propMap.getString("form").toUpperCase()));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "formSize")) {
            legend.setFormSize((float) propMap.getDouble("formSize"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "xEntrySpace")) {
            legend.setXEntrySpace((float) propMap.getDouble("xEntrySpace"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "yEntrySpace")) {
            legend.setYEntrySpace((float) propMap.getDouble("yEntrySpace"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "formToTextSpace")) {
            legend.setFormToTextSpace((float) propMap.getDouble("formToTextSpace"));
        }

        // Custom labels & colors
        if (BridgeUtils.validate(propMap, ReadableType.Map, "custom")) {
            ReadableMap customMap = propMap.getMap("custom");
            if (BridgeUtils.validate(customMap, ReadableType.Array, "colors") &&
                    BridgeUtils.validate(customMap, ReadableType.Array, "labels")) {

                ReadableArray colorsArray = customMap.getArray("colors");
                ReadableArray labelsArray = customMap.getArray("labels");

                if (colorsArray.size() == labelsArray.size()) {
                    // TODO null label should start a group
                    // TODO -2 color should avoid drawing a form
                    String[] labels = BridgeUtils.convertToStringArray(labelsArray);
                    String[] colors = BridgeUtils.convertToStringArray(colorsArray);

                    int[] colorsParsed = new int[colors.length];
                    for (int i = 0; i < colors.length; i++) {
                        colorsParsed[i] = Color.parseColor(colors[i]);
                    }

                    legend.setCustom(colorsParsed, labels);
                }
            }
        }

        // TODO resetCustom function
        // TODO extra

        chart.invalidate();     // TODO is this necessary? Looks like enabled is not refreshing without it
    }

    @ReactProp(name = "logEnabled")
    public void setLogEnabled(Chart chart, boolean enabled) {
        chart.setLogEnabled(enabled);
    }

    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(Chart chart, String color) {
        chart.setBackgroundColor(Color.parseColor(color));
    }

    @ReactProp(name = "description")
    public void setDescription(Chart chart, ReadableMap propMap) {
        if (BridgeUtils.validate(propMap, ReadableType.String, "text")) {
            chart.setDescription(propMap.getString("text"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "textColor")) {
            chart.setDescriptionColor(Color.parseColor(propMap.getString("textColor")));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "textSize")) {
            chart.setDescriptionTextSize((float) propMap.getDouble("textSize"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "positionX") &&
                BridgeUtils.validate(propMap, ReadableType.Number, "positionY")) {

            chart.setDescriptionPosition((float) propMap.getDouble("positionX"), (float) propMap.getDouble("positionY"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "fontFamily") ||
                BridgeUtils.validate(propMap, ReadableType.Number, "fontStyle")) {
            chart.setDescriptionTypeface(BridgeUtils.parseTypeface(propMap, "fontStyle", "fontFamily"));
        }
    }

    @ReactProp(name = "noDataText")
    public void setNoDataText(Chart chart, String noDataText) {
        chart.setNoDataText(noDataText);
    }

    @ReactProp(name = "noDataTextDescription")
    public void setNoDataTextDescription(Chart chart, String noDataTextDescription) {
        chart.setNoDataTextDescription(noDataTextDescription);
    }

    /**
     * Animations docs: https://github.com/PhilJay/MPAndroidChart/wiki/Animations
    */
    @ReactProp(name = "animation")
    public void setAnimation(Chart chart, ReadableMap propMap) {
        Integer durationX = null;
        Integer durationY = null;
        EasingOption easingX = EasingOption.Linear;
        EasingOption easingY = EasingOption.Linear;

        if (BridgeUtils.validate(propMap, ReadableType.Number, "durationX")) {
            durationX = propMap.getInt("durationX");
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "durationY")) {
            durationY = propMap.getInt("durationY");
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "easingX")) {
            easingX = EasingOption.valueOf(propMap.getString("easingX"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "easingY")) {
            easingY = EasingOption.valueOf(propMap.getString("easingY"));
        }

        if (durationX != null && durationY != null) {
            chart.animateXY(durationX, durationY, easingX, easingY);
        } else if (durationX != null) {
            chart.animateX(durationX, easingX);
        } else if (durationY != null) {
            chart.animateY(durationY, easingY);
        }
    }


    /**
     *
     * Dataset config details: https://github.com/PhilJay/MPAndroidChart/wiki/DataSet-classes-in-detail
     */
    @ReactProp(name = "data")
    public void setData(Chart chart, ReadableMap propMap) {

        if (!BridgeUtils.validate(propMap, ReadableType.Array, "xValues") ||
                !BridgeUtils.validate(propMap, ReadableType.Array, "datasets")) {
            return;
        }

        ChartData<IDataSet<U>> chartData = createData(BridgeUtils.convertToStringArray(propMap.getArray("xValues")));

        ReadableArray datasets = propMap.getArray("datasets");
        for (int i = 0; i < datasets.size(); i++) {
            ReadableMap dataset = datasets.getMap(i);

            // TODO validation
            ReadableArray yValues = dataset.getArray("yValues");
            String label = dataset.getString("label");

            ArrayList<U> entries = createEntries(yValues);

            IDataSet<U> lineDataSet = createDataSet(entries, label);

            if (BridgeUtils.validate(dataset, ReadableType.Map, "config")) {
                dataSetConfig(lineDataSet, dataset.getMap("config"));
            }

            chartData.addDataSet(lineDataSet);
        }

        chart.setData(chartData);
        chart.invalidate();
    }

    abstract ChartData<IDataSet<U>> createData(String[] xValues);
    abstract IDataSet<U> createDataSet(ArrayList<U> entries, String label);
    abstract void dataSetConfig(IDataSet<U> dataSet, ReadableMap config);

    ArrayList<U> createEntries(ReadableArray yValues) {
        ArrayList<U> entries = new ArrayList<>(yValues.size());
        for (int j = 0; j < yValues.size(); j++) {
            entries.add(createEntry(yValues, j));
        }
        return entries;
    }

    U createEntry(ReadableArray yValues, int index) {
        return (U) new Entry((float) yValues.getDouble(index), index);
    }

}

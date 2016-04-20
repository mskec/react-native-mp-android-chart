package com.github.reactNativeMPAndroidChart.charts;


import android.graphics.Color;
import android.graphics.Typeface;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.reactNativeMPAndroidChart.utils.BridgeUtils;

import java.util.ArrayList;
import java.util.Random;

public class LineChartManager extends SimpleViewManager<LineChart> {

    public static final String REACT_CLASS = "MPAndroidLineChart";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected LineChart createViewInstance(ThemedReactContext reactContext) {
        LineChart lineChart = new LineChart(reactContext);

//        setRandomData(lineChart);
//        lineChart.invalidate();

        return lineChart;
    }

    @ReactProp(name = "data")
    public void setData(LineChart chart, ReadableMap propMap) {

        if (BridgeUtils.validate(propMap, ReadableType.Array, "xValues") &&
                BridgeUtils.validate(propMap, ReadableType.Array, "datasets")) {

            LineData lineData = new LineData(BridgeUtils.convertToStringArray(propMap.getArray("xValues")));

            ReadableArray datasets = propMap.getArray("datasets");
            for (int i = 0; i < datasets.size(); i++) {
                ReadableMap dataset = datasets.getMap(i);

                // TODO validation
                ReadableArray yValues = dataset.getArray("yValues");
                String label = dataset.getString("label");

                ArrayList<Entry> entries = new ArrayList<>(yValues.size());
                for (int j = 0; j < yValues.size(); j++) {
                    entries.add(new Entry((float) yValues.getDouble(j), j));
                }

                LineDataSet lineDataSet = new LineDataSet(entries, label);

                // TODO config

                lineData.addDataSet(lineDataSet);
            }

            chart.setData(lineData);
            chart.invalidate();
        }
    }

    /**
     * More details about legend customization: https://github.com/PhilJay/MPAndroidChart/wiki/Legend
     *
     * fontStyle: NORMAL = 0, BOLD = 1, ITALIC = 2, BOLD_ITALIC = 3
     */
    @ReactProp(name = "legend")
    public void setLegend(LineChart chart, ReadableMap propMap) {
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

            String fontFamily = null;
            if (propMap.hasKey("fontFamily")) {
                fontFamily = propMap.getString("fontFamily");
            }

            int style = 0;
            if (propMap.hasKey("fontStyle")) {
                style = propMap.getInt("fontStyle");
            }

            legend.setTypeface(Typeface.create(fontFamily, style));
        }

        // Wrapping / clipping avoidance
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "wordWrapEnabled")) {
            legend.setWordWrapEnabled(propMap.getBoolean("wordWrapEnabled"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "maxSizePercent")) {
            legend.setMaxSizePercent((float) propMap.getDouble("maxSizePercent"));
        }

        // Customizing
        // TODO make sure position value is in LegendPosition enum, PIECHART_CENTER (only for PieChart)
        if (BridgeUtils.validate(propMap, ReadableType.String, "position")) {
            legend.setPosition(Legend.LegendPosition.valueOf(propMap.getString("position").toUpperCase()));
        }
        // TODO make sure form value is in LegendForm enum
        if (BridgeUtils.validate(propMap, ReadableType.String, "form")) {
            legend.setForm(Legend.LegendForm.valueOf(propMap.getString("form").toUpperCase()));
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

    private void setRandomData(LineChart lineChart) {
        int totalValues = 20;

        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 0; i < totalValues; i++) {
            xValues.add(Integer.valueOf(i).toString());
        }

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(randomDataSet(totalValues, "Data set 1"));
        dataSets.add(randomDataSet(totalValues, "Data set 2"));

        lineChart.setData(new LineData(xValues, dataSets));
    }

    private LineDataSet randomDataSet(int totalValues, String legendLabel) {
        Random random = new Random();

        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < totalValues; i++) {
            values.add(new Entry(random.nextInt(20) + 1, i));
        }

        return new LineDataSet(values, legendLabel);
    }

}


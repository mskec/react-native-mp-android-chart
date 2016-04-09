package com.github.reactNativeMPAndroidChart.charts;


import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

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

        Random random = new Random();

        ArrayList<String> xValues = new ArrayList<>();
        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            values.add(new Entry(random.nextInt(20) + 1, i));
            xValues.add(Integer.valueOf(i + 1).toString());
        }

        LineDataSet dataSet = new LineDataSet(values, "Test line chart 2016");
        LineData lineData = new LineData(xValues, dataSet);


        lineChart.setData(lineData);
        lineChart.invalidate();

        return lineChart;
    }

}

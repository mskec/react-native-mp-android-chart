package com.github.reactNativeMPAndroidChart.charts;


import android.graphics.Color;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.reactNativeMPAndroidChart.utils.BridgeUtils;
import com.github.reactNativeMPAndroidChart.utils.ChartDataSetConfigUtils;

import java.util.ArrayList;

public class PieChartManager extends ChartBaseManager<PieChart, Entry> {

    @Override
    public String getName() {
        return "MPAndroidPieChart";
    }

    @Override
    protected PieChart createViewInstance(ThemedReactContext reactContext) {
        return new PieChart(reactContext);
    }

    @Override
    ChartData createData(String[] xValues) {
        return new PieData(xValues);
    }

    @Override
    IDataSet createDataSet(ArrayList<Entry> entries, String label) {
        return new PieDataSet(entries, label);
    }

    @Override
    void dataSetConfig(IDataSet<Entry> dataSet, ReadableMap config) {
        PieDataSet pieDataSet = (PieDataSet) dataSet;

        ChartDataSetConfigUtils.commonConfig(pieDataSet, config);

        // PieDataSet only config
        if (BridgeUtils.validate(config, ReadableType.Number, "sliceSpace")) {
            pieDataSet.setSliceSpace((float) config.getDouble("sliceSpace"));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "selectionShift")) {
            pieDataSet.setSelectionShift((float) config.getDouble("selectionShift"));
        }
    }

    @ReactProp(name = "drawSliceText")
    public void setDrawSliceText(PieChart chart, boolean enabled) {
        chart.setDrawSliceText(enabled);
    }

    @ReactProp(name = "usePercentValues")
    public void setUsePercentValues(PieChart chart, boolean enabled) {
        chart.setUsePercentValues(enabled);
    }

    @ReactProp(name = "centerText")
    public void setCenterText(PieChart chart, String text) {
        chart.setCenterText(text);
    }

    @ReactProp(name = "centerTextRadiusPercent")
    public void setCenterTextRadiusPercent(PieChart chart, float radiusPercent) {
        chart.setCenterTextRadiusPercent(radiusPercent);
    }

    @ReactProp(name = "holeRadius")
    public void setHoleRadius(PieChart chart, float percent) {
        chart.setHoleRadius(percent);
    }

    @ReactProp(name = "holeColor")
    public void setHoleColor(PieChart chart, String color) {
        chart.setHoleColor(Color.parseColor(color));
    }

    @ReactProp(name = "transparentCircleRadius")
    public void setTransparentCircleRadius(PieChart chart, float percent) {
        chart.setTransparentCircleRadius(percent);
    }

    @ReactProp(name = "transparentCircleColor")
    public void setTransparentCircleColor(PieChart chart, String color) {
        chart.setTransparentCircleColor(Color.parseColor(color));
    }

    @ReactProp(name = "transparentCircleAlpha")
    public void setTransparentCircleAlpha(PieChart chart, int alpha) {
        chart.setTransparentCircleAlpha(alpha);
    }

    @ReactProp(name = "maxAngle")
    public void setMaxAngle(PieChart chart, float maxAngle) {
        chart.setMaxAngle(maxAngle);
    }

}

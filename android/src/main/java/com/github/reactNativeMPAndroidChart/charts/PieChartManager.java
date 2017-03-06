package com.github.reactNativeMPAndroidChart.charts;


import android.graphics.Color;
import android.view.View;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.reactNativeMPAndroidChart.utils.BridgeUtils;
import com.github.reactNativeMPAndroidChart.utils.ChartDataSetConfigUtils;
import com.github.reactNativeMPAndroidChart.utils.DataSetUtils;

import java.util.ArrayList;

public class PieChartManager extends ChartBaseManager<PieChart, PieEntry> {

    @Override
    public String getName() {
        return "MPAndroidPieChart";
    }

    @Override
    protected View createViewInstance(ThemedReactContext reactContext) {
        return new PieChart(reactContext);
    }

    @Override
    ChartData createData() {
        return new PieData();
    }

    @Override
    IDataSet<PieEntry> createDataSet(ArrayList<PieEntry> entries, String label) {
        return new PieDataSet(entries, label);
    }

    @Override
    void dataSetConfig(IDataSet<PieEntry> dataSet, ReadableMap config) {
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

    @Override
    PieEntry createEntry(ReadableArray values, int index) {
        return DataSetUtils.createPieEntry(values, index);
    }


    @ReactProp(name = "drawEntryLabels")
    public void setDrawSliceText(PieChart chart, boolean enabled) {
        chart.setDrawEntryLabels(enabled);
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

    @ReactProp(name = "entryLabelColor")
    public void setEntryLabelColor(PieChart chart, String color) {
        chart.setEntryLabelColor(Color.parseColor(color));
    }

    @ReactProp(name = "entryLabelTextSize")
    public void setEntryLabelTextSize(PieChart chart, float size) {
        chart.setEntryLabelTextSize(size);
    }


    @ReactProp(name = "maxAngle")
    public void setMaxAngle(PieChart chart, float maxAngle) {
        chart.setMaxAngle(maxAngle);
    }

}

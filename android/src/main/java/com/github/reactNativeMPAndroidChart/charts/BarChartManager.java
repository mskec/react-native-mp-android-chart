package com.github.reactNativeMPAndroidChart.charts;

import android.graphics.Color;
import android.view.View;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.reactNativeMPAndroidChart.utils.BridgeUtils;
import com.github.reactNativeMPAndroidChart.utils.ChartDataSetConfigUtils;
import com.github.reactNativeMPAndroidChart.utils.DataSetUtils;

import java.util.ArrayList;

public class BarChartManager extends BarLineChartBaseManager<BarChart, BarEntry> {

    @Override
    public String getName() {
        return "MPAndroidBarChart";
    }

    @Override
    protected View createViewInstance(ThemedReactContext reactContext) {
        BarChart barChart = new BarChart(reactContext);

        return barChart;
    }

    @Override
    ChartData createData() {
        return new BarData();
    }

    @Override
    IDataSet createDataSet(ArrayList<BarEntry> entries, String label) {
        return new BarDataSet(entries, label);
    }

    @Override
    BarEntry createEntry(ReadableArray values, int index) {
        return DataSetUtils.createBarEntry(values, index);
    }

    @Override
    void dataSetConfig(IDataSet<BarEntry> dataSet, ReadableMap config) {
        BarDataSet barDataSet = (BarDataSet) dataSet;

        ChartDataSetConfigUtils.commonConfig(barDataSet, config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(barDataSet, config);

        if (BridgeUtils.validate(config, ReadableType.String, "barShadowColor")) {
            barDataSet.setBarShadowColor(Color.parseColor(config.getString("barShadowColor")));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "highlightAlpha")) {
            barDataSet.setHighLightAlpha(config.getInt("highlightAlpha"));
        }
        if (BridgeUtils.validate(config, ReadableType.Array, "stackLabels")) {
            barDataSet.setStackLabels(BridgeUtils.convertToStringArray(config.getArray("stackLabels")));
        }

    }

    @ReactProp(name = "drawValueAboveBar")
    public void setDrawValueAboveBar(BarChart chart, boolean enabled) {
        chart.setDrawValueAboveBar(enabled);
    }

    @ReactProp(name = "drawBarShadow")
    public void setDrawBarShadow(BarChart chart, boolean enabled) {
        chart.setDrawBarShadow(enabled);
    }

}

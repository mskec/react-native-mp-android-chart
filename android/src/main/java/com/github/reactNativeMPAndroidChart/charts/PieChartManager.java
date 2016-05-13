package com.github.reactNativeMPAndroidChart.charts;


import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ThemedReactContext;
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
}

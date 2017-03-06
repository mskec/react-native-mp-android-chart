package com.github.reactNativeMPAndroidChart.charts;


import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.reactNativeMPAndroidChart.utils.BridgeUtils;
import com.github.reactNativeMPAndroidChart.utils.ChartDataSetConfigUtils;
import com.github.reactNativeMPAndroidChart.utils.DataSetUtils;

import java.util.ArrayList;

public class BubbleChartManager extends ChartBaseManager<BubbleChart, BubbleEntry> {

    @Override
    public String getName() {
        return "MPAndroidBubbleChart";
    }

    @Override
    protected BubbleChart createViewInstance(ThemedReactContext reactContext) {
        return new BubbleChart(reactContext);
    }

    @Override
    ChartData createData() {
        return new BubbleData();
    }


    @Override
    IDataSet createDataSet(ArrayList<BubbleEntry> entries, String label) {
        return new BubbleDataSet(entries, label);
    }

    @Override
    void dataSetConfig(IDataSet<BubbleEntry> dataSet, ReadableMap config) {
        BubbleDataSet bubbleDataSet = (BubbleDataSet) dataSet;

        ChartDataSetConfigUtils.commonConfig(bubbleDataSet, config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(bubbleDataSet, config);

        // BubbleDataSet only config
        if (BridgeUtils.validate(config, ReadableType.Number, "highlightCircleWidth")) {
            bubbleDataSet.setHighlightCircleWidth((float) config.getDouble("highlightCircleWidth"));
        }
    }

    @Override
    BubbleEntry createEntry(ReadableArray values, int index) {
        return DataSetUtils.createBubbleEntry(values, index);
    }
}

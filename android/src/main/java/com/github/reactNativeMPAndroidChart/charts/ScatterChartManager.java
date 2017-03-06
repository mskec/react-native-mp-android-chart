package com.github.reactNativeMPAndroidChart.charts;


import android.graphics.Color;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.charts.ScatterChart.ScatterShape;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.reactNativeMPAndroidChart.utils.BridgeUtils;
import com.github.reactNativeMPAndroidChart.utils.ChartDataSetConfigUtils;
import com.github.reactNativeMPAndroidChart.utils.DataSetUtils;

import java.util.ArrayList;

public class ScatterChartManager extends BarLineChartBaseManager<ScatterChart, Entry> {

    @Override
    public String getName() {
        return "MPAndroidScatterChart";
    }

    @Override
    protected ScatterChart createViewInstance(ThemedReactContext reactContext) {
        return new ScatterChart(reactContext);
    }

    @Override
    ChartData createData() {
        return new ScatterData();
    }

    @Override
    IDataSet createDataSet(ArrayList<Entry> entries, String label) {
        return new ScatterDataSet(entries, label);
    }

    @Override
    void dataSetConfig(IDataSet<Entry> dataSet, ReadableMap config) {
        ScatterDataSet scatterDataSet = (ScatterDataSet) dataSet;

        ChartDataSetConfigUtils.commonConfig(scatterDataSet, config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(scatterDataSet, config);
        ChartDataSetConfigUtils.commonLineScatterCandleRadarConfig(scatterDataSet, config);

        // ScatterDataSet only config
        if (BridgeUtils.validate(config, ReadableType.Number, "scatterShapeSize")) {
            scatterDataSet.setScatterShapeSize((float) config.getDouble("scatterShapeSize"));
        }
        if (BridgeUtils.validate(config, ReadableType.String, "scatterShape")) {
            scatterDataSet.setScatterShape(ScatterShape.valueOf(config.getString("scatterShape").toUpperCase()));
        }
        if (BridgeUtils.validate(config, ReadableType.String, "scatterShapeHoleColor")) {
            scatterDataSet.setScatterShapeHoleColor(Color.parseColor(config.getString("scatterShapeHoleColor")));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "scatterShapeHoleRadius")) {
            scatterDataSet.setScatterShapeHoleRadius((float) config.getDouble("scatterShapeHoleRadius"));
        }
    }

    @Override
    Entry createEntry(ReadableArray values, int index) {
        return DataSetUtils.createLineScatterEntry(values, index);
    }
}

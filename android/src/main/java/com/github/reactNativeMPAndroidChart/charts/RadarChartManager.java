package com.github.reactNativeMPAndroidChart.charts;


import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.reactNativeMPAndroidChart.utils.ChartDataSetConfigUtils;

import java.util.ArrayList;

public class RadarChartManager extends ChartBaseManager<RadarChart, Entry> {

    @Override
    public String getName() {
        return "MPAndroidRadarChart";
    }

    @Override
    protected RadarChart createViewInstance(ThemedReactContext reactContext) {
        return new RadarChart(reactContext);
    }

    @Override
    ChartData createData(String[] xValues) {
        return new RadarData(xValues);
    }

    @Override
    IDataSet createDataSet(ArrayList<Entry> entries, String label) {
        return new RadarDataSet(entries, label);
    }

    @Override
    void dataSetConfig(IDataSet<Entry> dataSet, ReadableMap config) {
        RadarDataSet radarDataSet = (RadarDataSet) dataSet;

        ChartDataSetConfigUtils.commonConfig(radarDataSet, config);
        ChartDataSetConfigUtils.commonLineScatterCandleRadarConfig(radarDataSet, config);
        ChartDataSetConfigUtils.commonLineRadarConfig(radarDataSet, config);

        // RadarDataSet only config
    }
}

package com.github.reactNativeMPAndroidChart.charts;


import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.reactNativeMPAndroidChart.utils.BridgeUtils;
import com.github.reactNativeMPAndroidChart.utils.ChartDataSetConfigUtils;
import com.github.reactNativeMPAndroidChart.utils.DataSetUtils;

import java.util.ArrayList;

public class RadarChartManager extends YAxisChartBase<RadarChart, RadarEntry> {

    @Override
    public String getName() {
        return "MPAndroidRadarChart";
    }

    @Override
    protected RadarChart createViewInstance(ThemedReactContext reactContext) {
        return new RadarChart(reactContext);
    }

    @Override
    public void setYAxis(Chart chart, ReadableMap propMap) {
        RadarChart radarChart = (RadarChart) chart;
        YAxis axis = radarChart.getYAxis();

        setCommonAxisConfig(chart, axis, propMap);
        setYAxisConfig(axis, propMap);
    }

    @Override
    ChartData createData() {
        return new RadarData();
    }

    @Override
    IDataSet createDataSet(ArrayList<RadarEntry> entries, String label) {
        return new RadarDataSet(entries, label);
    }

    @Override
    void dataSetConfig(IDataSet<RadarEntry> dataSet, ReadableMap config) {
        RadarDataSet radarDataSet = (RadarDataSet) dataSet;

        ChartDataSetConfigUtils.commonConfig(radarDataSet, config);
        ChartDataSetConfigUtils.commonLineScatterCandleRadarConfig(radarDataSet, config);
        ChartDataSetConfigUtils.commonLineRadarConfig(radarDataSet, config);

        // RadarDataSet only config
    }

    @Override
    RadarEntry createEntry(ReadableArray values, int index) {
        return DataSetUtils.createRadarEntry(values, index);
    }

    @ReactProp(name = "skipWebLineCount")
    public void setSkipWebLineCount(RadarChart chart, int count) {
        chart.setSkipWebLineCount(count);
    }

}

package com.github.reactNativeMPAndroidChart.charts;


import android.graphics.Color;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;

import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.reactNativeMPAndroidChart.utils.BridgeUtils;
import com.github.reactNativeMPAndroidChart.utils.ChartDataSetConfigUtils;
import com.github.reactNativeMPAndroidChart.utils.DataSetUtils;

import java.util.ArrayList;

public class LineChartManager extends BarLineChartBaseManager<LineChart, Entry> {

    @Override
    public String getName() {
        return "MPAndroidLineChart";
    }

    @Override
    protected LineChart createViewInstance(ThemedReactContext reactContext) {
        return new LineChart(reactContext);
    }

    @Override
    ChartData createData() {
        return new LineData();
    }


    @Override
    IDataSet createDataSet(ArrayList<Entry> entries, String label) {
        return new LineDataSet(entries, label);
    }

    @Override
    void dataSetConfig(IDataSet<Entry> dataSet, ReadableMap config) {
        LineDataSet lineDataSet = (LineDataSet) dataSet;

        ChartDataSetConfigUtils.commonConfig(lineDataSet, config);
        ChartDataSetConfigUtils.commonBarLineScatterCandleBubbleConfig(lineDataSet, config);
        ChartDataSetConfigUtils.commonLineScatterCandleRadarConfig(lineDataSet, config);
        ChartDataSetConfigUtils.commonLineRadarConfig(lineDataSet, config);

        // LineDataSet only config
        if (BridgeUtils.validate(config, ReadableType.Number, "circleRadius")) {
            lineDataSet.setCircleRadius((float) config.getDouble("circleRadius"));
        }
        if (BridgeUtils.validate(config, ReadableType.Boolean, "drawCircles")) {
            lineDataSet.setDrawCircles(config.getBoolean("drawCircles"));
        }
        if (BridgeUtils.validate(config, ReadableType.String, "mode")) {
            lineDataSet.setMode(LineDataSet.Mode.valueOf(config.getString("mode")));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "drawCubicIntensity")) {
            lineDataSet.setCubicIntensity((float) config.getDouble("drawCubicIntensity"));
        }
        if (BridgeUtils.validate(config, ReadableType.String, "circleColor")) {
            lineDataSet.setCircleColor(Color.parseColor(config.getString("circleColor")));
        }
        if (BridgeUtils.validate(config, ReadableType.Array, "circleColors")) {
            lineDataSet.setCircleColors(BridgeUtils.parseColors(config.getArray("circleColors")));
        }
        if (BridgeUtils.validate(config, ReadableType.String, "circleColorHole")) {
            lineDataSet.setCircleColorHole(Color.parseColor(config.getString("circleColorHole")));
        }
        if (BridgeUtils.validate(config, ReadableType.Boolean, "drawCircleHole")) {
            lineDataSet.setDrawCircleHole(config.getBoolean("drawCircleHole"));
        }
        if (BridgeUtils.validate(config, ReadableType.Map, "dashedLine")) {
            ReadableMap dashedLine = config.getMap("dashedLine");
            float lineLength = 0;
            float spaceLength = 0;
            float phase = 0;

            if (BridgeUtils.validate(dashedLine, ReadableType.Number, "lineLength")) {
                lineLength = (float) dashedLine.getDouble("lineLength");
            }
            if (BridgeUtils.validate(dashedLine, ReadableType.Number, "spaceLength")) {
                spaceLength = (float) dashedLine.getDouble("spaceLength");
            }
            if (BridgeUtils.validate(dashedLine, ReadableType.Number, "phase")) {
                phase = (float) dashedLine.getDouble("phase");
            }

            lineDataSet.enableDashedLine(lineLength, spaceLength, phase);
        }
    }

    @Override
    Entry createEntry(ReadableArray values, int index) {
        return DataSetUtils.createLineScatterEntry(values, index);
    }


}

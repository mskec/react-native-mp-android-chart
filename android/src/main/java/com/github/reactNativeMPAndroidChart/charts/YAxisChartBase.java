package com.github.reactNativeMPAndroidChart.charts;

import android.graphics.Color;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.reactNativeMPAndroidChart.utils.BridgeUtils;

public abstract class YAxisChartBase<T extends Chart, U extends Entry> extends ChartBaseManager<T, U> {

    /**
     * yAxis config details: https://github.com/PhilJay/MPAndroidChart/wiki/YAxis
     */
    @ReactProp(name = "yAxis")
    public abstract void setYAxis(Chart chart, ReadableMap propMap);

    protected void setYAxisConfig(YAxis axis, ReadableMap propMap) {
        if (BridgeUtils.validate(propMap, ReadableType.Number, "axisMaxValue")) {
            axis.setAxisMaxValue((float) propMap.getDouble("axisMaxValue"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "axisMinValue")) {
            axis.setAxisMinValue((float) propMap.getDouble("axisMinValue"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "inverted")) {
            axis.setInverted(propMap.getBoolean("inverted"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "spaceTop")) {
            axis.setSpaceTop((float) propMap.getDouble("spaceTop"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "spaceBottom")) {
            axis.setSpaceBottom((float) propMap.getDouble("spaceBottom"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "showOnlyMinMax")) {
            axis.setShowOnlyMinMax(propMap.getBoolean("showOnlyMinMax"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "labelCount")) {
            boolean labelCountForce = false;
            if (BridgeUtils.validate(propMap, ReadableType.Boolean, "labelCountForce")) {
                labelCountForce = propMap.getBoolean("labelCountForce");
            }
            axis.setLabelCount(propMap.getInt("labelCount"), labelCountForce);
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "position")) {
            axis.setPosition(YAxis.YAxisLabelPosition.valueOf(propMap.getString("position")));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "granularity")) {
            axis.setGranularity((float) propMap.getDouble("granularity"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "granularityEnabled")) {
            axis.setGranularityEnabled(propMap.getBoolean("granularityEnabled"));
        }


        // formatting
        if (BridgeUtils.validate(propMap, ReadableType.String, "valueFormatter")) {
            String valueFormatter = propMap.getString("valueFormatter");

            if ("largeValue".equals(valueFormatter)) {
                axis.setValueFormatter(new LargeValueFormatter());
            } else if ("percent".equals(valueFormatter)) {
                axis.setValueFormatter(new PercentFormatter());
            }
        }

        // TODO docs says the remaining config needs to be applied before setting data. Test it
        // zero line
        if (BridgeUtils.validate(propMap, ReadableType.Map, "zeroLine")) {
            ReadableMap zeroLineConfig = propMap.getMap("zeroLine");

            if (BridgeUtils.validate(zeroLineConfig, ReadableType.Boolean, "enabled")) {
                axis.setDrawZeroLine(zeroLineConfig.getBoolean("enabled"));
            }
            if (BridgeUtils.validate(zeroLineConfig, ReadableType.Number, "lineWidth")) {
                axis.setZeroLineWidth((float) zeroLineConfig.getDouble("lineWidth"));
            }
            if (BridgeUtils.validate(zeroLineConfig, ReadableType.String, "lineColor")) {
                axis.setZeroLineColor(Color.parseColor(zeroLineConfig.getString("lineColor")));
            }
        }
    }

}

package com.github.reactNativeMPAndroidChart.utils;

import android.graphics.Color;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.LineRadarDataSet;
import com.github.mikephil.charting.data.LineScatterCandleRadarDataSet;

/**
 * https://github.com/PhilJay/MPAndroidChart/wiki/The-DataSet-class
 * https://github.com/PhilJay/MPAndroidChart/wiki/DataSet-classes-in-detail
 */
public class ChartDataSetConfigUtils {

    public static void commonConfig(DataSet dataSet, ReadableMap config) {
        // Setting main color
        if (BridgeUtils.validate(config, ReadableType.String, "color")) {
            dataSet.setColor(Color.parseColor(config.getString("color")));
        }
        if (BridgeUtils.validate(config, ReadableType.Array, "colors")) {
            dataSet.setColors(BridgeUtils.parseColors(config.getArray("colors")));
        }

        // TODO more config to add: https://github.com/PhilJay/MPAndroidChart/wiki/The-DataSet-class

        if (BridgeUtils.validate(config, ReadableType.Boolean, "drawValues")) {
            dataSet.setDrawValues(config.getBoolean("drawValues"));
        }

        if(BridgeUtils.validate(config, ReadableType.Boolean, "highlightEnabled")) {
            dataSet.setHighlightEnabled(config.getBoolean("highlightEnabled"));
        }

    }

    public static void commonBarLineScatterCandleBubbleConfig(BarLineScatterCandleBubbleDataSet dataSet, ReadableMap config) {
        if (BridgeUtils.validate(config, ReadableType.String, "highlightColor")) {
            dataSet.setHighLightColor(Color.parseColor(config.getString("highlightColor")));
        }
    }
    
    public static void commonLineScatterCandleRadarConfig(LineScatterCandleRadarDataSet dataSet, ReadableMap config) {
        if (BridgeUtils.validate(config, ReadableType.Boolean, "drawHighlightIndicators")) {
            dataSet.setDrawHighlightIndicators(config.getBoolean("drawHighlightIndicators"));
        }
        if (BridgeUtils.validate(config, ReadableType.Boolean, "drawVerticalHighlightIndicator")) {
            dataSet.setDrawVerticalHighlightIndicator(config.getBoolean("drawVerticalHighlightIndicator"));
        }
        if (BridgeUtils.validate(config, ReadableType.Boolean, "drawHorizontalHighlightIndicator")) {
            dataSet.setDrawHorizontalHighlightIndicator(config.getBoolean("drawHorizontalHighlightIndicator"));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "highlightLineWidth")) {
            dataSet.setHighlightLineWidth((float) config.getDouble("highlightLineWidth"));
        }
    }

    public static void commonLineRadarConfig(LineRadarDataSet dataSet, ReadableMap config) {
        if (BridgeUtils.validate(config, ReadableType.String, "fillColor")) {
            dataSet.setFillColor(Color.parseColor(config.getString("fillColor")));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "fillAlpha")) {
            dataSet.setFillAlpha(config.getInt("fillAlpha"));
        }
        // TODO setFillDrawable android.graphics.drawable.Drawable
        if (BridgeUtils.validate(config, ReadableType.Boolean, "drawFilled")) {
            dataSet.setDrawFilled(config.getBoolean("drawFilled"));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "lineWidth")) {
            float lineWidth = (float) config.getDouble("lineWidth");
            if (lineWidth >= 0.2f && lineWidth < 10f) {
                dataSet.setLineWidth(lineWidth);
            }
        }
    }


}

package com.github.reactNativeMPAndroidChart.charts;

import android.graphics.Color;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.data.Entry;

public abstract class BarLineChartBaseManager<T extends BarLineChartBase, U extends Entry> extends ChartBaseManager<T, U> {

    @ReactProp(name = "drawGridBackground")
    public void setDrawGridBackground(BarLineChartBase chart, boolean enabled) {
        chart.setDrawGridBackground(enabled);
    }

    @ReactProp(name = "gridBackgroundColor")
    public void setGridBackgroundColor(BarLineChartBase chart, String color) {
        chart.setGridBackgroundColor(Color.parseColor(color));
    }

    @ReactProp(name = "drawBorders")
    public void setDrawBorders(BarLineChartBase chart, boolean enabled) {
        chart.setDrawBorders(enabled);
    }

    @ReactProp(name = "borderColor")
    public void setBorderColor(BarLineChartBase chart, String color) {
        chart.setBorderColor(Color.parseColor(color));
    }

    @ReactProp(name = "borderWidth")
    public void setBorderWidth(BarLineChartBase chart, float width) {
        chart.setBorderWidth(width);
    }

    @ReactProp(name = "maxVisibleValueCount")
    public void setMaxVisibleValueCount(BarLineChartBase chart, int count) {
        chart.setMaxVisibleValueCount(count);
    }

    @ReactProp(name = "autoScaleMinMaxEnabled")
    public void setAutoScaleMinMaxEnabled(BarLineChartBase chart, boolean enabled) {
        chart.setAutoScaleMinMaxEnabled(enabled);
    }

    @ReactProp(name = "keepPositionOnRotation")
    public void setKeepPositionOnRotation(BarLineChartBase chart, boolean enabled) {
        chart.setKeepPositionOnRotation(enabled);
    }

}

package com.github.reactNativeMPAndroidChart.charts;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;

public class CustomFormatter implements YAxisValueFormatter {

    private String mFormat;

    public CustomFormatter(String value) {
        this.mFormat = value;
    }

    @Override
    public String getFormattedValue(float value, YAxis yAxis) {
        return String.format(mFormat, value);
    }
}

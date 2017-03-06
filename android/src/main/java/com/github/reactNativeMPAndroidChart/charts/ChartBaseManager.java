package com.github.reactNativeMPAndroidChart.charts;

import android.content.Context;
import android.graphics.Color;
import android.content.res.ColorStateList;
import android.os.Build;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.animation.Easing.EasingOption;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.reactNativeMPAndroidChart.markers.OvalMarker;
import com.github.reactNativeMPAndroidChart.markers.RNMarkerView;
import com.github.reactNativeMPAndroidChart.markers.RectangleMarker;
import com.github.reactNativeMPAndroidChart.utils.BridgeUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class ChartBaseManager<T extends Chart, U extends Entry> extends SimpleViewManager {


    /**
     * More details about legend customization: https://github.com/PhilJay/MPAndroidChart/wiki/Legend
     */
    @ReactProp(name = "legend")
    public void setLegend(T chart, ReadableMap propMap) {
        Legend legend = chart.getLegend();

        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "enabled")) {
            legend.setEnabled(propMap.getBoolean("enabled"));
        }

        // Styling
        if (BridgeUtils.validate(propMap, ReadableType.String, "textColor")) {
            legend.setTextColor(Color.parseColor(propMap.getString("textColor")));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "textSize")) {
            legend.setTextSize((float) propMap.getDouble("textSize"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "fontFamily") ||
                BridgeUtils.validate(propMap, ReadableType.Number, "fontStyle")) {
            legend.setTypeface(BridgeUtils.parseTypeface(chart.getContext(), propMap, "fontStyle", "fontFamily"));
        }

        // Wrapping / clipping avoidance
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "wordWrapEnabled")) {
            legend.setWordWrapEnabled(propMap.getBoolean("wordWrapEnabled"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "maxSizePercent")) {
            legend.setMaxSizePercent((float) propMap.getDouble("maxSizePercent"));
        }

        // Customizing
        if (BridgeUtils.validate(propMap, ReadableType.String, "position")) {
            legend.setPosition(LegendPosition.valueOf(propMap.getString("position").toUpperCase()));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "form")) {
            legend.setForm(LegendForm.valueOf(propMap.getString("form").toUpperCase()));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "formSize")) {
            legend.setFormSize((float) propMap.getDouble("formSize"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "xEntrySpace")) {
            legend.setXEntrySpace((float) propMap.getDouble("xEntrySpace"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "yEntrySpace")) {
            legend.setYEntrySpace((float) propMap.getDouble("yEntrySpace"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "formToTextSpace")) {
            legend.setFormToTextSpace((float) propMap.getDouble("formToTextSpace"));
        }

        // Custom labels & colors
        if (BridgeUtils.validate(propMap, ReadableType.Map, "custom")) {
            ReadableMap customMap = propMap.getMap("custom");
            if (BridgeUtils.validate(customMap, ReadableType.Array, "colors") &&
                    BridgeUtils.validate(customMap, ReadableType.Array, "labels")) {

                ReadableArray colorsArray = customMap.getArray("colors");
                ReadableArray labelsArray = customMap.getArray("labels");

                if (colorsArray.size() == labelsArray.size()) {
                    // TODO null label should start a group
                    // TODO -2 color should avoid drawing a form
                    String[] labels = BridgeUtils.convertToStringArray(labelsArray);
                    String[] colors = BridgeUtils.convertToStringArray(colorsArray);

                    int length = colors.length;

                    int[] colorsParsed = new int[length];
                    LegendEntry[] legendEntries = new LegendEntry[labels.length];
                    for (int i = 0; i < length; i++) {
                        colorsParsed[i] = Color.parseColor(colors[i]);

                        legendEntries[i] = new LegendEntry();
                        legendEntries[i].formColor = colorsParsed[i];
                        legendEntries[i].label = labels[i];
                    }

                    legend.setCustom(legendEntries);
                }
            }
        }

        // TODO resetCustom function
        // TODO extra

        chart.invalidate();     // TODO is this necessary? Looks like enabled is not refreshing without it
    }

    @ReactProp(name = "logEnabled")
    public void setLogEnabled(Chart chart, boolean enabled) {
        chart.setLogEnabled(enabled);
    }

    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(Chart chart, String color) {
        chart.setBackgroundColor(Color.parseColor(color));
    }

    @ReactProp(name = "description")
    public void setDescription(Chart chart, ReadableMap propMap) {

        Description description = new Description();

        if (BridgeUtils.validate(propMap, ReadableType.String, "text")) {
            description.setText(propMap.getString("text"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "textColor")) {
            description.setTextColor(Color.parseColor(propMap.getString("textColor")));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "textSize")) {
            description.setTextSize((float) propMap.getDouble("textSize"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "positionX") &&
                BridgeUtils.validate(propMap, ReadableType.Number, "positionY")) {
            description.setPosition((float) propMap.getDouble("positionX"), (float) propMap.getDouble("positionY"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "fontFamily") ||
                BridgeUtils.validate(propMap, ReadableType.Number, "fontStyle")) {
            description.setTypeface(BridgeUtils.parseTypeface(chart.getContext(), propMap, "fontStyle", "fontFamily"));
        }

        chart.setDescription(description);
    }

    @ReactProp(name = "noDataText")
    public void setNoDataText(Chart chart, String noDataText) {
        chart.setNoDataText(noDataText);
    }

    @ReactProp(name = "touchEnabled")
    public void setTouchEnabled(Chart chart, boolean enabled) {
        chart.setTouchEnabled(enabled);
    }

    @ReactProp(name = "dragDecelerationEnabled")
    public void setDragDecelerationEnabled(Chart chart, boolean enabled) {
        chart.setDragDecelerationEnabled(enabled);
    }

    @ReactProp(name = "dragDecelerationFrictionCoef")
    public void setDragDecelerationFrictionCoef(Chart chart, float coef) {
        chart.setDragDecelerationFrictionCoef(coef);
    }

    /**
     * Animations docs: https://github.com/PhilJay/MPAndroidChart/wiki/Animations
     */
    @ReactProp(name = "animation")
    public void setAnimation(Chart chart, ReadableMap propMap) {
        Integer durationX = null;
        Integer durationY = null;
        EasingOption easingX = EasingOption.Linear;
        EasingOption easingY = EasingOption.Linear;

        if (BridgeUtils.validate(propMap, ReadableType.Number, "durationX")) {
            durationX = propMap.getInt("durationX");
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "durationY")) {
            durationY = propMap.getInt("durationY");
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "easingX")) {
            easingX = EasingOption.valueOf(propMap.getString("easingX"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "easingY")) {
            easingY = EasingOption.valueOf(propMap.getString("easingY"));
        }

        if (durationX != null && durationY != null) {
            chart.animateXY(durationX, durationY, easingX, easingY);
        } else if (durationX != null) {
            chart.animateX(durationX, easingX);
        } else if (durationY != null) {
            chart.animateY(durationY, easingY);
        }
    }

    /**
     * xAxis config details: https://github.com/PhilJay/MPAndroidChart/wiki/XAxis
     */
    @ReactProp(name = "xAxis")
    public void setXAxis(Chart chart, ReadableMap propMap) {
        XAxis axis = chart.getXAxis();

        setCommonAxisConfig(chart, axis, propMap);

        if (BridgeUtils.validate(propMap, ReadableType.Number, "labelRotationAngle")) {
            axis.setLabelRotationAngle((float) propMap.getDouble("labelRotationAngle"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "avoidFirstLastClipping")) {
            axis.setAvoidFirstLastClipping(propMap.getBoolean("avoidFirstLastClipping"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "position")) {
            axis.setPosition(XAxisPosition.valueOf(propMap.getString("position")));
        }
    }

    @ReactProp(name = "marker")
    public void setMarker(Chart chart, ReadableMap propMap) {
        if (!BridgeUtils.validate(propMap, ReadableType.Boolean, "enabled") || !propMap.getBoolean("enabled")) {
            chart.setMarker(null);
            return;
        }

        RNMarkerView marker = null;
        String type = "rectangle";

        if (BridgeUtils.validate(propMap, ReadableType.String, "type")) {
            type = propMap.getString("type");
        }

        if ("rectangle".equals(type)) {
            marker = new RectangleMarker(chart.getContext());

        } else if ("oval".equals(type)) {
            marker = new OvalMarker(chart.getContext());

        } else {
            try {
                marker = (RNMarkerView) Class.forName(type)
                        .getConstructor(Context.class)
                        .newInstance(chart.getContext());

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (marker == null) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                BridgeUtils.validate(propMap, ReadableType.String, "backgroundTint")) {
            marker.getMarkerContent()
                    .setBackgroundTintList(
                            ColorStateList.valueOf(Color.parseColor(propMap.getString("backgroundTint")))
                    );
        }

        if (BridgeUtils.validate(propMap, ReadableType.String, "textColor")) {
            marker.getTvContent().setTextColor(Color.parseColor(propMap.getString("textColor")));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "textSize")) {
            marker.getTvContent().setTextSize(propMap.getInt("textSize"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "fontFamily") ||
                BridgeUtils.validate(propMap, ReadableType.Number, "fontStyle")) {
            marker.getTvContent()
                    .setTypeface(BridgeUtils.parseTypeface(chart.getContext(), propMap, "fontStyle", "fontFamily"));
        }

        chart.setMarker(marker);
    }

    /**
     * General axis config details: https://github.com/PhilJay/MPAndroidChart/wiki/The-Axis
     */
    protected void setCommonAxisConfig(Chart chart, AxisBase axis, ReadableMap propMap) {
        // what is drawn
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "enabled")) {
            axis.setEnabled(propMap.getBoolean("enabled"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "drawLabels")) {
            axis.setDrawLabels(propMap.getBoolean("drawLabels"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "drawAxisLine")) {
            axis.setDrawAxisLine(propMap.getBoolean("drawAxisLine"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "drawGridLines")) {
            axis.setDrawGridLines(propMap.getBoolean("drawGridLines"));
        }

        // style
        if (BridgeUtils.validate(propMap, ReadableType.String, "textColor")) {
            axis.setTextColor(Color.parseColor(propMap.getString("textColor")));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "textSize")) {
            axis.setTextSize((float) propMap.getDouble("textSize"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "fontFamily") ||
                BridgeUtils.validate(propMap, ReadableType.Number, "fontStyle")) {
            axis.setTypeface(BridgeUtils.parseTypeface(chart.getContext(), propMap, "fontStyle", "fontFamily"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "gridColor")) {
            axis.setGridColor(Color.parseColor(propMap.getString("gridColor")));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "gridLineWidth")) {
            axis.setGridLineWidth((float) propMap.getDouble("gridLineWidth"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.String, "axisLineColor")) {
            axis.setAxisLineColor(Color.parseColor(propMap.getString("axisLineColor")));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "axisLineWidth")) {
            axis.setAxisLineWidth((float) propMap.getDouble("axisLineWidth"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Map, "gridDashedLine")) {
            ReadableMap gridDashedLine = propMap.getMap("gridDashedLine");
            float lineLength = 0;
            float spaceLength = 0;
            float phase = 0;

            if (BridgeUtils.validate(gridDashedLine, ReadableType.Number, "lineLength")) {
                lineLength = (float) gridDashedLine.getDouble("lineLength");
            }
            if (BridgeUtils.validate(gridDashedLine, ReadableType.Number, "spaceLength")) {
                spaceLength = (float) gridDashedLine.getDouble("spaceLength");
            }
            if (BridgeUtils.validate(gridDashedLine, ReadableType.Number, "phase")) {
                phase = (float) gridDashedLine.getDouble("phase");
            }

            axis.enableGridDashedLine(lineLength, spaceLength, phase);
        }

        // limit lines
        if (BridgeUtils.validate(propMap, ReadableType.Array, "limitLines")) {
            ReadableArray limitLines = propMap.getArray("limitLines");

            for (int i = 0; i < limitLines.size(); i++) {
                if (!ReadableType.Map.equals(limitLines.getType(i))) {
                    continue;
                }

                ReadableMap limitLineMap = limitLines.getMap(i);
                if (BridgeUtils.validate(limitLineMap, ReadableType.Number, "limit")) {
                    LimitLine limitLine = new LimitLine((float) limitLineMap.getDouble("limit"));

                    if (BridgeUtils.validate(limitLineMap, ReadableType.String, "label")) {
                        limitLine.setLabel(limitLineMap.getString("label"));
                    }
                    if (BridgeUtils.validate(limitLineMap, ReadableType.String, "lineColor")) {
                        limitLine.setLineColor(Color.parseColor(limitLineMap.getString("lineColor")));
                    }
                    if (BridgeUtils.validate(limitLineMap, ReadableType.Number, "lineWidth")) {
                        limitLine.setLineWidth((float) limitLineMap.getDouble("lineWidth"));
                    }

                    axis.addLimitLine(limitLine);
                }

            }
        }
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "drawLimitLinesBehindData")) {
            axis.setDrawLimitLinesBehindData(propMap.getBoolean("drawLimitLinesBehindData"));
        }

        if (BridgeUtils.validate(propMap, ReadableType.Number, "axisMaxValue")) {
            axis.setAxisMaximum((float) propMap.getDouble("axisMaxValue"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "axisMinValue")) {
            axis.setAxisMinimum((float) propMap.getDouble("axisMinValue"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "granularity")) {
            axis.setGranularity((float) propMap.getDouble("granularity"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Boolean, "granularityEnabled")) {
            axis.setGranularityEnabled(propMap.getBoolean("granularityEnabled"));
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "labelCount")) {
            boolean labelCountForce = false;
            if (BridgeUtils.validate(propMap, ReadableType.Boolean, "labelCountForce")) {
                labelCountForce = propMap.getBoolean("labelCountForce");
            }
            axis.setLabelCount(propMap.getInt("labelCount"), labelCountForce);
        }

        // formatting
        if (BridgeUtils.validate(propMap, ReadableType.String, "valueFormatter")) {
            String valueFormatter = propMap.getString("valueFormatter");

            if ("largeValue".equals(valueFormatter)) {
                axis.setValueFormatter(new LargeValueFormatter());
            } else if ("percent".equals(valueFormatter)) {
                axis.setValueFormatter(new PercentFormatter());
            } else {
                axis.setValueFormatter(new CustomFormatter(valueFormatter));
            }
        } else if (BridgeUtils.validate(propMap, ReadableType.Array, "valueFormatter")) {
            axis.setValueFormatter(new IndexAxisValueFormatter(BridgeUtils.convertToStringArray(propMap.getArray("valueFormatter"))));
        }
    }

    /**
     * Dataset config details: https://github.com/PhilJay/MPAndroidChart/wiki/DataSet-classes-in-detail
     */
    @ReactProp(name = "data")
    public void setData(Chart chart, ReadableMap propMap) {
        if (!BridgeUtils.validate(propMap, ReadableType.Array, "dataSets")) {
            return;
        }

        ChartData<IDataSet<U>> chartData = createData();

        ReadableArray dataSets = propMap.getArray("dataSets");
        for (int i = 0; i < dataSets.size(); i++) {
            ReadableMap dataSet = dataSets.getMap(i);

            // TODO validation
            ReadableArray values = dataSet.getArray("values");
            String label = dataSet.getString("label");

            ArrayList<U> entries = createEntries(values);

            IDataSet<U> lineDataSet = createDataSet(entries, label);

            if (BridgeUtils.validate(dataSet, ReadableType.Map, "config")) {
                dataSetConfig(lineDataSet, dataSet.getMap("config"));
            }

            chartData.addDataSet(lineDataSet);
        }

        chart.setData(chartData);

        chart.invalidate();
    }

    abstract ChartData<IDataSet<U>> createData();

    abstract IDataSet<U> createDataSet(ArrayList<U> entries, String label);

    abstract void dataSetConfig(IDataSet<U> dataSet, ReadableMap config);

    ArrayList<U> createEntries(ReadableArray yValues) {
        ArrayList<U> entries = new ArrayList<>(yValues.size());
        for (int j = 0; j < yValues.size(); j++) {
            if (!yValues.isNull(j)) {
                entries.add(createEntry(yValues, j));
            }
        }
        return entries;
    }

    abstract U createEntry(ReadableArray values, int index);

}

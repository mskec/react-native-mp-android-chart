package com.github.reactNativeMPAndroidChart.utils;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarEntry;

/**
 * Created by xudong on 27/02/2017.
 */

public class DataSetUtils {
    public static Entry createLineScatterEntry(ReadableArray values, int index) {
        Entry entry;

        float x = index;
        if (ReadableType.Map.equals(values.getType(index))) {
            ReadableMap map = values.getMap(index);
            if (map.hasKey("x")) {
                x = (float) map.getDouble("x");
            }

            if (!BridgeUtils.validate(map, ReadableType.Number, "y")) {
                throw new IllegalArgumentException("Invalid Line/Scatter Entry data");
            }

            entry = new Entry(x, (float) map.getDouble("y"), map);
        } else if (ReadableType.Number.equals(values.getType(index))) {
            entry = new Entry(x, (float) values.getDouble(index));
        } else {
            throw new IllegalArgumentException("Unexpected entry type: " + values.getType(index));
        }

        return entry;
    }

    public static BarEntry createBarEntry(ReadableArray values, int index) {
        BarEntry entry;

        float x = index;
        if (ReadableType.Map.equals(values.getType(index))) {
            ReadableMap map = values.getMap(index);

            if (map.hasKey("x")) {
                x = (float) map.getDouble("x");
            }

            if (ReadableType.Array.equals(map.getType("y"))) {
                entry = new BarEntry(x, BridgeUtils.convertToFloatArray(map.getArray("y")));
            } else if (ReadableType.Number.equals(map.getType("y"))) {
                entry = new BarEntry(x, (float) map.getDouble("y"));
            } else {
                throw new IllegalArgumentException("Unexpected entry type: " + values.getType(index));
            }

            entry.setData(map);

        } else if (ReadableType.Array.equals(values.getType(index))) {
            entry = new BarEntry(x, BridgeUtils.convertToFloatArray(values.getArray(index)));
        } else if (ReadableType.Number.equals(values.getType(index))) {
            entry = new BarEntry(x, (float) values.getDouble(index));
        } else {
            throw new IllegalArgumentException("Unexpected entry type: " + values.getType(index));
        }

        return entry;
    }

    public static BubbleEntry createBubbleEntry(ReadableArray values, int index) {
        if (!ReadableType.Map.equals(values.getType(index))) {
            throw new IllegalArgumentException("Invalid BubbleEntry data");
        }

        ReadableMap map = values.getMap(index);

        float x = index;
        if (map.hasKey("x")) {
            x = (float) map.getDouble("x");
        }

        if (!BridgeUtils.validate(map, ReadableType.Number, "y") ||
                !BridgeUtils.validate(map, ReadableType.Number, "size")) {
            throw new IllegalArgumentException("Invalid BubbleEntry data");
        }


        float y = (float) map.getDouble("y");
        float size = (float) map.getDouble("size");

        BubbleEntry bubbleEntry = new BubbleEntry(x, y, size, map);

        return bubbleEntry;
    }

    public static CandleEntry createCandleEntry(ReadableArray values, int index) {
        if (!ReadableType.Map.equals(values.getType(index))) {
            throw new IllegalArgumentException();
        }

        ReadableMap map = values.getMap(index);

        float x = index;
        if (map.hasKey("x")) {
            x = (float) map.getDouble("x");
        }

        if (
                !BridgeUtils.validate(map, ReadableType.Number, "shadowH") ||
                        !BridgeUtils.validate(map, ReadableType.Number, "shadowL") ||
                        !BridgeUtils.validate(map, ReadableType.Number, "open") ||
                        !BridgeUtils.validate(map, ReadableType.Number, "close")) {
            throw new IllegalArgumentException("CandleStick data must contain: shadowH, shadowL, open and close values");
        }

        float shadowH = (float) map.getDouble("shadowH");
        float shadowL = (float) map.getDouble("shadowL");
        float open = (float) map.getDouble("open");
        float close = (float) map.getDouble("close");

        CandleEntry candleEntry = new CandleEntry(x, shadowH, shadowL, open, close, map);

        return candleEntry;
    }

    public static PieEntry createPieEntry(ReadableArray values, int index) {
        PieEntry entry;

        if (ReadableType.Map.equals(values.getType(index))) {
            ReadableMap map = values.getMap(index);

            if (!BridgeUtils.validate(map, ReadableType.Number, "value")) {
                throw new IllegalArgumentException("Invalid PieEntry data");
            }

            float value = (float) map.getDouble("value");

            if (BridgeUtils.validate(map, ReadableType.String, "label")) {
                entry = new PieEntry(value, map.getString("label"), map);
            } else {
                entry = new PieEntry(value, map);
            }
        } else if (ReadableType.Number.equals(values.getType(index))) {
            entry = new PieEntry((float) values.getDouble(index));
        } else {
            throw new IllegalArgumentException("Unexpected entry type: " + values.getType(index));
        }

        return entry;
    }

    public static RadarEntry createRadarEntry(ReadableArray values, int index) {
        RadarEntry entry;

        if (ReadableType.Map.equals(values.getType(index))) {
            ReadableMap map = values.getMap(index);

            if (!BridgeUtils.validate(map, ReadableType.Number, "value")) {
                throw new IllegalArgumentException("Invalid RadarEntry data");
            }

            float value = (float) map.getDouble("value");
            entry = new RadarEntry(value, map);
        } else if (ReadableType.Number.equals(values.getType(index))) {
            entry = new RadarEntry((float) values.getDouble(index));
        } else {
            throw new IllegalArgumentException("Unexpected entry type: " + values.getType(index));
        }
        return entry;
    }


}

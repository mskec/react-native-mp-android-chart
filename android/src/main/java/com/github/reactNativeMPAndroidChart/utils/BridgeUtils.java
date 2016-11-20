package com.github.reactNativeMPAndroidChart.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.views.text.ReactFontManager;

public class BridgeUtils {

    public static boolean validate(ReadableMap map, ReadableType propType, String propName) {
        return map.hasKey(propName) && propType.equals(map.getType(propName));
    }

    public static int[] convertToIntArray(ReadableArray readableArray) {
        int[] array = new int[readableArray.size()];

        for (int i = 0; i < readableArray.size(); i++) {
            if (!ReadableType.Number.equals(readableArray.getType(i))) {
                throw new IllegalArgumentException("Expecting array of numbers");
            }
            array[i] = readableArray.getInt(i);
        }

        return array;
    }

    public static float[] convertToFloatArray(ReadableArray readableArray) {
        float[] array = new float[readableArray.size()];

        for (int i = 0; i < readableArray.size(); i++) {
            if (!ReadableType.Number.equals(readableArray.getType(i))) {
                throw new IllegalArgumentException("Expecting array of numbers");
            }
            array[i] = (float) readableArray.getDouble(i);
        }

        return array;
    }

    public static String[] convertToStringArray(ReadableArray readableArray) {
        String[] array = new String[readableArray.size()];

        for (int i = 0; i < readableArray.size(); i++) {
            if (!ReadableType.String.equals(readableArray.getType(i))) {
                throw new IllegalArgumentException("Expecting array of strings");
            }
            array[i] = readableArray.getString(i);
        }

        return array;
    }


    public static int[] parseColors(ReadableArray readableArray) {
        int[] array = new int[readableArray.size()];

        for (int i = 0; i < readableArray.size(); i++) {
            if (!ReadableType.String.equals(readableArray.getType(i))) {
                throw new IllegalArgumentException("Expecting array of strings");
            }
            array[i] = Color.parseColor(readableArray.getString(i));
        }

        return array;
    }

    /**
     * fontStyle: NORMAL = 0, BOLD = 1, ITALIC = 2, BOLD_ITALIC = 3
     */
    public static Typeface parseTypeface(Context context, ReadableMap propMap, String styleKey, String familyKey) {
        String fontFamily = null;
        if (propMap.hasKey(familyKey)) {
            fontFamily = propMap.getString(familyKey);
        }

        int style = 0;
        if (propMap.hasKey(styleKey)) {
            style = propMap.getInt(styleKey);
        }

        return ReactFontManager.getInstance().getTypeface(fontFamily, style, context.getAssets());
    }

}

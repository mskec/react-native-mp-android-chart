package com.github.reactNativeMPAndroidChart;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.github.reactNativeMPAndroidChart.charts.BarChartManager;
import com.github.reactNativeMPAndroidChart.charts.LineChartManager;
import com.github.reactNativeMPAndroidChart.charts.PieChartManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MPAndroidChartPackage implements ReactPackage {

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Arrays.<NativeModule>asList();
    }

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(
            new LineChartManager(),
            new BarChartManager(),
            new PieChartManager()
        );
    }

}

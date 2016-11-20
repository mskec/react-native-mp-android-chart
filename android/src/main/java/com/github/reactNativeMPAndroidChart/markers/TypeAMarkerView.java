package com.github.reactNativeMPAndroidChart.markers;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.Utils;
import com.github.reactNativeMPAndroidChart.R;

/**
 * Type A marker.
 */
public class TypeAMarkerView extends MarkerView {

    private RelativeLayout markerContent;
    private TextView tvContent;

    public TypeAMarkerView(Context context) {
        super(context, R.layout.type_a_marker_view);

        tvContent = (TextView) findViewById(R.id.type_a_tvContent);
        markerContent = (RelativeLayout) findViewById(R.id.type_a_markerContent);
    }

    // callbacks every time the MarkerView is redrawn, can be used to update the content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        if (e instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) e;

            tvContent.setText(Utils.formatNumber(ce.getClose(), 2, true));
        } else {

            tvContent.setText(Utils.formatNumber(e.getVal(), 0, true));
        }
    }

    @Override
    public int getXOffset(float xpos) {
        // this will center the marker-view horizontally
        return -(getWidth() / 2);
    }

    @Override
    public int getYOffset(float ypos) {
        // this will cause the marker-view to be above the selected value
        return -getHeight();
    }

    public RelativeLayout getMarkerContent() {
        return markerContent;
    }

    public TextView getTvContent() {
        return tvContent;
    }
}

package com.omarAndsattar.timepickerdialog.widget;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/9/14
 * update time:
 * email: 723526676@qq.com
 */
public class CircularViewMode implements ItemViewMode {

    private float mScalingRatio = 0.001f;

    public CircularViewMode() {}

    @Override
    public void applyToView(View v, RecyclerView parent) {
        float halfHeight = v.getHeight() * 0.5f;
        float parentHalfHeight = parent.getHeight() * 0.5f;
        float y = v.getY();

        float scale = 1.0f - Math.abs(parentHalfHeight - halfHeight - y) * mScalingRatio;
        ViewCompat.setScaleX(v, scale);
        ViewCompat.setScaleY(v, scale);
    }
}

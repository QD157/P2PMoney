package com.example.wyf.p2pmoney.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Created by WYF on 2017/3/13.
 */

public class DrawableUtils {

    public static GradientDrawable getGradientDrawable(int color, int radius){
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(radius);
        return gradientDrawable;
    }

    public static StateListDrawable getSelector(Drawable normal, Drawable press){
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, press);
        stateListDrawable.addState(new int[]{}, normal);
        return stateListDrawable;
    }
}

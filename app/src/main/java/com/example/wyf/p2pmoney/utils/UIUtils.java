package com.example.wyf.p2pmoney.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.example.wyf.p2pmoney.global.MyApplication;

/**
 * Created by WYF on 2017/3/18.
 */

public class UIUtils {

    public static Context getContext(){
        return MyApplication.context;
    }

    public static Handler getHandler(){
        return MyApplication.handler;
    }

    public static int getColor(int id){
        return getContext().getResources().getColor(id);
    }

    public static View inflate(int id){
        return View.inflate(getContext(), id, null);
    }

    public static String[] getStringArr(int id){
        return getContext().getResources().getStringArray(id);
    }

    public static int dip2px(int dip){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int)(dip * density + 0.5);
    }

    public static int px2dip(int px){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int)(px/density);
    }
}

package com.example.wyf.p2pmoney.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

/**
 * Created by WYF on 2017/3/18.
 */

public class MyApplication extends Application {

    public static Context context = null;
    public static Handler handler = null;
    public static Thread mainThread = null;
    public static int mainThreadId = 0;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();
        mainThreadId = Process.myTid();
        //CrashHandler.getInstance().init(this);
    }
}

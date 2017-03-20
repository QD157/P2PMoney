package com.example.wyf.p2pmoney.global;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by WYF on 2017/3/18.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler crashHandler;

    private CrashHandler() {}

    public static CrashHandler getInstance(){
        if(crashHandler == null){
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }

    private Context ctx;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    public void init(Context ctx){
        this.ctx = ctx;
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (isHandler(e)){
            handlerException(t, e);
        }else{
            defaultUncaughtExceptionHandler.uncaughtException(t, e);
        }
    }

    private void handlerException(Thread t, Throwable e) {
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(ctx, "系统出现未知异常，即将退出", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        collectionException(e);
        try {
            t.sleep(2000);
            AppManager.getInstance().removeAll();
            Process.killProcess(Process.myPid());
            System.exit(0);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private void collectionException(Throwable e) {
        final String deviceInfo = Build.DEVICE + Build.VERSION.SDK_INT + Build.MODEL + Build.PRODUCT;
        final String errorInfo = e.getMessage();
        new Thread(){
            @Override
            public void run() {
                Log.e(TAG, "deviceInfo: " + deviceInfo + "message: " + errorInfo);
            }
        }.start();
    }

    private boolean isHandler(Throwable e) {
        if (e == null) {
            return false;
        }else{
            return true;
        }
    }
}

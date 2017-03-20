package com.example.wyf.p2pmoney.global;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by WYF on 2017/3/18.
 */

public class AppManager {

    private static Stack<Activity> activityStack = new Stack<>();

    private static AppManager appManager;

    private AppManager(){}

    public static AppManager getInstance(){
        if(appManager == null){
            appManager = new AppManager();
        }
        return appManager;
    }

    public void addActivity(Activity activity){
        activityStack.add(activity);
    }

    public void removeActivity(Activity activity){
        for (Activity a : activityStack){
            if(a.getClass().equals(activity.getClass())){
                a.finish();
                activityStack.remove(a);
                break;
            }
        }
    }

    public void removeCurent(){
        Activity activity = activityStack.lastElement();
        activity.finish();
        activityStack.remove(activity);
    }

    public void removeAll(){
        for (Activity activity : activityStack){
            activity.finish();
            activityStack.remove(activity);
        }
    }

    public int getSize(){
        return activityStack.size();
    }
}

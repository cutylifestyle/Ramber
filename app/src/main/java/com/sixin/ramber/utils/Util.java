package com.sixin.ramber.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhou
 */

public class Util {

    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    private static WeakReference<Activity> sTopActivityWeakRef;

    // TODO: 2018/1/8 这个警告需要注意，还有添加的activity会不会发生内存泄露的问题
    private static List<Activity> sActivityList = new LinkedList<>();

    private static Application.ActivityLifecycleCallbacks sCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            sActivityList.add(activity);
            setsTopActivityWeakRef(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            sActivityList.remove(activity);
        }
    };

    private Util(){
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init(@NonNull final Application app){
        sApplication = app;
        sApplication.registerActivityLifecycleCallbacks(sCallbacks);
    }

    private static void setsTopActivityWeakRef(Activity activity){
        if(sTopActivityWeakRef == null || !activity.equals(sTopActivityWeakRef.get())){
            sTopActivityWeakRef = new WeakReference<>(activity);
        }
    }

    public static Application getApp(){
        if(sApplication != null){
            return sApplication;
        }
        throw new NullPointerException("u should first init...");
    }

    static WeakReference<Activity> getsTopActivityWeakRef(){
        return sTopActivityWeakRef;
    }

    static List<Activity> getsActivityList(){
        return sActivityList;
    }

}

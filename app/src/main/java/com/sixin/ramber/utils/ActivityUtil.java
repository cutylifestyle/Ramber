package com.sixin.ramber.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;

import java.util.List;

/**
 * @author zhou
 */

public class ActivityUtil {

    private ActivityUtil(){
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取栈顶 Activity
     *
     * @return 栈顶 Activity
     */
    public static Activity getTopActivity(){
        if(Util.getsTopActivityWeakRef() != null){
            Activity activity = Util.getsTopActivityWeakRef().get();
            if(activity != null){
                return activity;
            }
        }
        List<Activity> activityList = Util.getsActivityList();
        int size = activityList.size();
        return size>0 ? activityList.get(size-1):null;
    }

    /**
     * 启动 Activity
     *
     * @param cls Activity 类
     */
    public static void startActivity(@NonNull final Class<?> cls) {
        Context context = getTopActivityOrApp();
        startActivity(context,null,context.getPackageName(),cls.getName(),null);
    }

    /**
     * 启动 Activity
     *
     * @param extras extras
     * @param cls    Activity 类
     */
    public static void startActivity(@NonNull final Class<?> cls,
                                     @NonNull Bundle extras) {
        Context context = getTopActivityOrApp();
        startActivity(context,extras,context.getPackageName(),cls.getName(),null);
    }

    /**
     * 启动 Activity
     *
     * @param extras   extras
     * @param activity activity
     * @param cls      Activity 类
     */
    public static void startActivity(@NonNull final Activity activity,
                                     @NonNull final Class<?> cls,
                                     @NonNull final Bundle extras) {
        startActivity(activity,extras,activity.getPackageName(),cls.getName(),null);
    }

    /**
     * 启动 Activity
     *
     * @param activity  activity
     * @param enterAnim 入场动画
     * @param exitAnim  出场动画
     */
    @SuppressLint("ObsoleteSdkInt")
    public static void startActivity(@NonNull final Activity activity,
                                     @NonNull final Class<?> cls,
                                     @NonNull final Bundle extras,
                                     @AnimRes final int enterAnim,
                                     @AnimRes final int exitAnim) {
        startActivity(activity,extras,activity.getPackageName(),cls.getName(),getOptionsBundle(activity,enterAnim,exitAnim));
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            activity.overridePendingTransition(enterAnim, exitAnim);
        }
    }

    private static Bundle getOptionsBundle(final Context context,
                                           final int enterAnim,
                                           final int exitAnim) {
        return ActivityOptionsCompat.makeCustomAnimation(context, enterAnim, exitAnim).toBundle();
    }

    private static Context getTopActivityOrApp(){
        Activity topActivity = getTopActivity();
        return topActivity == null ? Util.getApp():topActivity;
    }

    private static void startActivity(final Context context,
                                      final Bundle extras,
                                      final String pkg,
                                      final String cls,
                                      final Bundle options){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if(extras != null){
            intent.putExtras(extras);
        }
        intent.setComponent(new ComponentName(pkg, cls));
        startActivity(intent,context,options);
    }

    private static void startActivity(final Intent intent,
                                      final Context context,
                                      final Bundle options){
        if(!(context instanceof Activity)){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if(options != null){
            context.startActivity(intent,options);
        }else{
            context.startActivity(intent);
        }
    }

    /**
     * 结束 Activity,没有退出动画
     *
     * @param activity activity
     */
    public static void finishActivity(@NonNull final Activity activity){
        finishActivity(activity,false);
    }


    public static void finishActivity(@NonNull final Activity activity,
                                      @NonNull final boolean isLoadAnim){
        activity.finish();
        if(!isLoadAnim){
            activity.overridePendingTransition(0,android.R.anim.slide_out_right);
        }
    }


}

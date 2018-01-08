package com.sixin.ramber;

import android.app.Application;

import com.sixin.ramber.utils.Util;

/**
 * @author zhou
 */

public class RamberApp extends Application {

    //TODO APP类还没有进行一些初始化的设置，清单文件中的主题部分

    @Override
    public void onCreate() {
        super.onCreate();
        Util.init(this);
    }
}

package com.sixin.ramber.utils;

import android.os.Environment;

/**
 * @author zhou
 */

public class EnvironmentUtil {

    private EnvironmentUtil(){
        throw new UnsupportedOperationException("u can't instantiate me ...");
    }

    /**
     * 是否安转了存储
     * @return {@code true} 已经安装<br> {@code false} 未安装
     * */
    public static boolean isMediaMounted(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取存储卡根目录的绝对路径
     * */
    public static String getExternalStorageDirectoryPath(){
        return !isMediaMounted()? null:
                Environment.getExternalStorageDirectory().getAbsolutePath();
    }

}

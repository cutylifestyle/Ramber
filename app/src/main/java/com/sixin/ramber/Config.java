package com.sixin.ramber;

/**
 * @author zhou
 */

public class Config {
    // TODO: 2018/1/8 这个部分看要不要采用 SharedPreference来处理，提供单行注释
    public static final String INTERNAL_STORAGE = "内部存储设备";

    public static final String ACTIVITY_SETTINGS = "activity_settings";
    public static final String NAVIGATE_SETTINGS = "navigate_settings";

    public static final int REQUEST_READ_EXTERNAL_STORAGE = 1;

    public static final String KEY_NOW_PLAYING = "now_playing_selector";
    public static final String KEY_DARK_THEME = "dark_theme";
    public static final String KEY_PRIMARY_COLOR = "primary_color";
    public static final String KEY_ACCENT_COLOR = "accent_color";
    public static final String KEY_COLORED_STATUS_BAR ="colored_status_bar";
    public static final String KEY_COLORED_NAV_BAR = "colored_nav_bar";
    public static final String KEY_PAUSE_DETACH = "pause_detach";
    public static final String KEY_LOCK_SCREEN = "lock_screen";

    //recyclerView中GridLayout的列数
    public static final int SPAN_COUNT = 2;

    private Config(){
        throw new UnsupportedOperationException("u can't instantiate me...");
    }



}

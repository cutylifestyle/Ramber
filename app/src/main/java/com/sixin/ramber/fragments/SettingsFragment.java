package com.sixin.ramber.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;


import com.sixin.ramber.Config;
import com.sixin.ramber.R;


public class SettingsFragment extends PreferenceFragment {
    // TODO: 2018/1/8 xml中还有很多东西没有配置 如果我非要在滑动的过程中toolBar上移怎么办
    // TODO: 2018/1/8 源码分析---->替换preference控件，处理逻辑
    public SettingsFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefrences);
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        dispatchTask(preference);
        return true;
    }

    private void dispatchTask(Preference preference) {
        switch (preference.getKey()) {
            case Config.KEY_NOW_PLAYING://正在播放
                choiceNowPlayingStyle(preference);
                break;
            case Config.KEY_DARK_THEME://夜间模式
                switchTheme(preference);
                break;
            case Config.KEY_PRIMARY_COLOR://基础色
                choicePrimaryColor(preference);
                break;
            case Config.KEY_ACCENT_COLOR://强调色
                choiceAccentColor(preference);
                break;
            case Config.KEY_COLORED_STATUS_BAR://状态栏着色
                choiceStatusBarColor(preference);
                break;
            case Config.KEY_COLORED_NAV_BAR://导航栏着色
                choiceNavBarColor(preference);
                break;
            case Config.KEY_PAUSE_DETACH://拔出时暂停
                isPausePlaying(preference);
                break;
            case Config.KEY_LOCK_SCREEN://锁屏
                isDisplayLockScreen(preference);
                break;
        }
    }

    private void isDisplayLockScreen(Preference preference) {

    }

    private void isPausePlaying(Preference preference) {

    }

    private void choiceNavBarColor(Preference preference) {

    }

    private void choiceStatusBarColor(Preference preference) {

    }

    private void choiceAccentColor(Preference preference) {

    }

    private void choicePrimaryColor(Preference preference) {
    }

    private void switchTheme(Preference preference) {

    }

    private void choiceNowPlayingStyle(Preference preference) {

    }


}

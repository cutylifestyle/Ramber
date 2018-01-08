package com.sixin.ramber.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.sixin.ramber.R;

public class SettingsActivity extends BaseActivity {
    // TODO: 2018/1/8 settingActivity的主题样式问题
    private Toolbar mToolbarSettings;
    private FrameLayout mFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
        setActionBar();
    }

    private void initViews() {
        mToolbarSettings = findViewById(R.id.toolbar_settings);
        mFragmentContainer = findViewById(R.id.fragment_container);
    }

    private void setActionBar(){
        setSupportActionBar(mToolbarSettings);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}

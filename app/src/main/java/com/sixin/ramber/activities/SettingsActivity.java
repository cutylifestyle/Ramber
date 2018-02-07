package com.sixin.ramber.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sixin.ramber.R;
import com.sixin.ramber.fragments.SettingsFragment;
import com.sixin.ramber.utils.ActivityUtil;

public class SettingsActivity extends BaseActivity {
    // TODO: 2018/1/8 settingActivity的主题样式问题
    private Toolbar mToolbarSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
        setActionBar();
        bindFragment(SettingsFragment.newInstance(),R.id.fragment_container);
    }

    private void initViews() {
        mToolbarSettings = findViewById(R.id.toolbar_settings);
    }

    private void setActionBar(){
        setSupportActionBar(mToolbarSettings);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ActivityUtil.finishActivity(this,true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

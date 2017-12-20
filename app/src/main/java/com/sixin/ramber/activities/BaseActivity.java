package com.sixin.ramber.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;


public class BaseActivity extends com.afollestad.aesthetic.AestheticActivity {
    //TODO AestheticActivity中需要复写key()方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void bindFragment(Fragment fragment,int containerId){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);
        //TODO commitAllowingStateLoss commit 的区别，主界面调用的是第一个方法
        transaction.commit();
    }
}

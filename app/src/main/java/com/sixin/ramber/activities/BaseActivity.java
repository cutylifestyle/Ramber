package com.sixin.ramber.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;


public class BaseActivity extends com.afollestad.aesthetic.AestheticActivity {
    //TODO AestheticActivity中需要复写key()方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void addFragmentV4(Fragment fragment, String tag, int containerId){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(containerId, fragment, tag);
        //TODO commitAllowingStateLoss commit 的区别，主界面调用的是第一个方法
        transaction.commit();
    }

    protected void bindFragment(android.app.Fragment fragment, int containerId){
        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.commit();
    }

    /**
     * 碎片是否存在
     * @param fragmentTag 碎片的标签
     * @return boolean {@code true}: 存在<br>{@code false}: 不存在
     * */
    protected boolean isFragmentExist(@NonNull String fragmentTag){
        List<Fragment> fragments =  getSupportFragmentManager().getFragments();
        if(fragments != null && fragments.size() > 0){
            for(Fragment fragment:fragments){
                if(fragmentTag.equals(fragment.getTag())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 根据标签显示碎片
     * @param fragmentTag 碎片的标签
     * */
    protected void showFragmentByTag(@NonNull String fragmentTag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        List<Fragment> fragments =  fragmentManager.getFragments();
        if(fragments != null && fragments.size() > 0){
            for(Fragment fragment:fragments){
                if(fragmentTag.equals(fragment.getTag())){
                    transaction.show(fragment);
                }else{
                    transaction.hide(fragment);
                }
            }
            transaction.commit();
        }
    }

    /**
     * 隐藏所有碎片
     * */
    protected void hideAllFragments(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        List<Fragment> fragments =  fragmentManager.getFragments();
        if(fragments != null && fragments.size() > 0 ){
            for(Fragment fragment:fragments){
                transaction.hide(fragment);
            }
            transaction.commit();
        }
    }

    /**
     * 显示或添加碎片
     * */
    protected void showOrAddFragment(@NonNull String fragmentTag,@NonNull Fragment fragment, int containerId){
        boolean result = isFragmentExist(fragmentTag);
        if(result){
            showFragmentByTag(fragmentTag);
        }else{
            hideAllFragments();
            addFragmentV4(fragment,fragmentTag,containerId);
        }
    }
}

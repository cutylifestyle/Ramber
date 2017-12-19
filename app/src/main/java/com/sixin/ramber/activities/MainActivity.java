package com.sixin.ramber.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.sixin.ramber.R;
import com.sixin.ramber.fragments.MainFragment;

/**
 * @author zhou
 * */

public class MainActivity extends BaseActivity {
    //TODO 侧滑抽屉部分划出的时候没有响应,是因为布局顺序颠倒的原因，需要看源码
    //TODO drawable文件夹命名的秘密，以及style文件夹命名的秘密
    //TODO 矢量图
    //TODO 碎片源码分析   CoordinatorLayout AppBarLayout ToolBar

    private NavigationView mNavMain;
    private DrawerLayout mDLMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initGUI();
        setViewsListener();
    }

    private void initGUI() {
        mNavMain.getMenu().findItem(R.id.nav_library).setChecked(true);
        bindFragment(MainFragment.newInstance());
    }

    private void setViewsListener() {
        mNavMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_library:
                        //TODO 需要优化的点：默认情况下初始加载这个界面，但是点击这个菜单界面又会重新创建
                        mNavMain.setCheckedItem(item.getItemId());
                        mDLMain.closeDrawers();
                        bindFragment(MainFragment.newInstance());
                        break;
                    case R.id.nav_playlist:
                        break;
                    case R.id.nav_folders:
                        break;
                    case R.id.nav_play_queue:
                        break;
                    case R.id.nav_now_playing:
                        break;
                    case R.id.nav_setting:
                        break;
                    case R.id.nav_about:
                        break;
                }
                //TODO 返回false与返回true之间存在什么区别
                return false;
            }
        });
    }

    private void initViews() {
        mDLMain = findViewById(R.id.dl_main);
        mNavMain = findViewById(R.id.nv_main);
    }


    private void bindFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        //TODO commitAllowingStateLoss commit 的区别，主界面调用的是第一个方法
        transaction.commit();
    }


}

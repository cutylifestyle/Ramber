package com.sixin.ramber.activities;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sixin.ramber.Config;
import com.sixin.ramber.R;
import com.sixin.ramber.RamberDrawerListener;
import com.sixin.ramber.fragments.FoldersFragment;
import com.sixin.ramber.fragments.MainFragment;
import com.sixin.ramber.fragments.PlayListFragment;
import com.sixin.ramber.utils.ActivityUtil;
import com.sixin.ramber.utils.permissionsutil.PermissionsDenied;
import com.sixin.ramber.utils.permissionsutil.PermissionsGranted;
import com.sixin.ramber.utils.permissionsutil.PermissionsNoNeeded;
import com.sixin.ramber.utils.permissionsutil.PermissionsUtil;

/**
 * @author zhou
 * */

public class MainActivity extends BaseActivity {
    //TODO AppBarLayout TabLayout 碎片中的菜单方法
    // TODO: 2017/12/27 Gallery
    // TODO: 2018/1/3 behavior的原理
    // TODO: 2018/1/3 集成下拉刷新控件
    // TODO: 2018/1/7 baseRecyclerAdapter
    private String menuItem = "";

    private NavigationView mNavMain;
    private DrawerLayout mDLMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setNavigationListener();
        setDrawerListener();
        checkPermissionAndThenLoad();
    }

    private void initGUI() {
        mNavMain.getMenu().findItem(R.id.nav_library).setChecked(true);
        addFragmentV4(MainFragment.newInstance(),Config.MAIN_FRAGMENT,R.id.fragment_container);
    }

    private void setNavigationListener() {
        mNavMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_library:
                        setNavigationState(item,true);
                        menuItem = Config.MAIN_FRAGMENT;
                        break;
                    case R.id.nav_playlist:
                        setNavigationState(item,true);
                        menuItem = Config.PLAY_LIST_FRAGMENT;
                        break;
                    case R.id.nav_folders:
                        setNavigationState(item,true);
                        menuItem =Config.FOLDERS_FRAGMENT;
                        break;
                    case R.id.nav_play_queue:
                        break;
                    case R.id.nav_now_playing:
                        break;
                    case R.id.nav_setting:
                        setNavigationState(item,false);
                        startSettingsActivity();
                        break;
                    case R.id.nav_about:
                        break;
                }
                return true;
            }
        });
    }

    private void setDrawerListener() {
        mDLMain.addDrawerListener(new RamberDrawerListener(){
            @Override
            public void onDrawerClosed(View drawerView) {
                switch (menuItem){
                    case Config.MAIN_FRAGMENT:
                        showOrAddFragment(Config.MAIN_FRAGMENT,
                                MainFragment.newInstance(),
                                R.id.fragment_container);
                        break;
                    case Config.PLAY_LIST_FRAGMENT:
                        showOrAddFragment(Config.PLAY_LIST_FRAGMENT,
                                PlayListFragment.newInstance(),
                                R.id.fragment_container);
                        break;
                    case Config.FOLDERS_FRAGMENT:
                        showOrAddFragment(Config.FOLDERS_FRAGMENT,
                                FoldersFragment.newInstance(),
                                R.id.fragment_container);
                        break;


                }
            }
        });
    }

    private void startSettingsActivity() {
        Bundle extras = new Bundle();
        extras.putString(Config.ACTIVITY_SETTINGS,Config.NAVIGATE_SETTINGS);
        ActivityUtil.startActivity(this,SettingsActivity.class,extras);
    }

    private void setNavigationState(@NonNull MenuItem item,boolean isClose) {
        mNavMain.setCheckedItem(item.getItemId());
        if(isClose){
            mDLMain.closeDrawers();
        }
    }

    private void initViews() {
        mDLMain = findViewById(R.id.dl_main);
        mNavMain = findViewById(R.id.nv_main);
    }

    private void checkPermissionAndThenLoad() {
        PermissionsUtil.requestPermissions(this,
                Config.REQUEST_READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionsUtil.onRequestPermissionsResult(this,
                requestCode,permissions,grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionsGranted
    private void permissionsGranted(){
        initGUI();
    }

    @PermissionsDenied
    private void permissionsDenied(){
        finish();
    }

    @PermissionsNoNeeded
    private void permissionsNoNeeded(){
        initGUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDLMain.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

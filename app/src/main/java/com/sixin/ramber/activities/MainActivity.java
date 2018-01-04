package com.sixin.ramber.activities;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.sixin.ramber.R;
import com.sixin.ramber.fragments.FoldersFragment;
import com.sixin.ramber.fragments.MainFragment;
import com.sixin.ramber.fragments.PlayListFragment;
import com.sixin.ramber.utils.permissionsutil.PermissionsDenied;
import com.sixin.ramber.utils.permissionsutil.PermissionsGranted;
import com.sixin.ramber.utils.permissionsutil.PermissionsNoNeeded;
import com.sixin.ramber.utils.permissionsutil.PermissionsUtil;

/**
 * @author zhou
 * */

public class MainActivity extends BaseActivity {
    private static final int REQUEST_READ_EXTERNAL_STORAGE = 1;
    //TODO 矢量动画，动画start以后在什么时候执行的
    //TODO AppBarLayout TabLayout 碎片中的菜单方法
    // TODO: 2017/12/27 Gallery
    // TODO: 2018/1/3 behavivor的原理
    // TODO: 2018/1/3 集成下拉刷新控件
    private NavigationView mNavMain;
    private DrawerLayout mDLMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setViewsListener();
        checkPermissionAndThenLoad();
    }

    private void initGUI() {
        mNavMain.getMenu().findItem(R.id.nav_library).setChecked(true);
        bindFragment(MainFragment.newInstance(),R.id.fragment_container);
    }

    private void setViewsListener() {
        mNavMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_library:
                        //TODO 需要优化的点：默认情况下初始加载这个界面，但是点击这个菜单界面又会重新创建
                        setNavigationState(item);
                        bindFragment(MainFragment.newInstance(),R.id.fragment_container);
                        break;
                    case R.id.nav_playlist:
                        setNavigationState(item);
                        bindFragment(PlayListFragment.newInstance(), R.id.fragment_container);
                        break;
                    case R.id.nav_folders:
                        setNavigationState(item);
                        bindFragment(FoldersFragment.newInstance(),R.id.fragment_container);
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
                return true;
            }
        });
    }

    private void setNavigationState(@NonNull MenuItem item) {
        mNavMain.setCheckedItem(item.getItemId());
        mDLMain.closeDrawers();
    }

    private void initViews() {
        mDLMain = findViewById(R.id.dl_main);
        mNavMain = findViewById(R.id.nv_main);
    }

    private void checkPermissionAndThenLoad() {
        PermissionsUtil.requestPermissions(this,
                REQUEST_READ_EXTERNAL_STORAGE,
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

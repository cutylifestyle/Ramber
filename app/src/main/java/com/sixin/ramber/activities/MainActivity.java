package com.sixin.ramber.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sixin.ramber.R;

/**
 * @author zhou
 * */

public class MainActivity extends AppCompatActivity {
    //TODO 侧滑抽屉部分划出的时候没有响应,是因为布局顺序颠倒的原因，需要看源码
    //TODO drawable文件夹命名的秘密，以及style文件夹命名的秘密
    //TODO 矢量图
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

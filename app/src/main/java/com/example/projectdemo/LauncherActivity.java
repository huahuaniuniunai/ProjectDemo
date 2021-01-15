package com.example.projectdemo;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.example.projectdemo.mvp.BaseActivity;

public class LauncherActivity extends BaseActivity {
    private TextView skip;
    private int TIME = 3;
    private boolean isSkip = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        skip = (TextView)findViewById(R.id.skip);
        // 将欢迎界面系统自带的标题栏隐藏
        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case -2:
                        skip.setText("跳过( "+TIME+"s )");
                        break;
                    case 1:
                        // 这里记得要判断是否选择跳过，防止重复加载LoginActivity
                        if (!isSkip) {
                            LoginActivity.actionStart(LauncherActivity.this);
                            isSkip = true;
                            finish();
                        }
                        break;
                }
            }
        };

        new Thread(new Runnable() {  // 开启一个线程倒计时
            @Override
            public void run() {
                for (; TIME>0; TIME--){
                    handler.sendEmptyMessage(-2);
                    if (TIME<=0)
                        break;
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(1);
            }
        }).start();


        skip.setOnClickListener(new View.OnClickListener() {  // 设置跳过按键的监听器
            @Override
            public void onClick(View v) {
                LoginActivity.actionStart(LauncherActivity.this);
                isSkip = true;
                finish();
            }
        });
    }
}
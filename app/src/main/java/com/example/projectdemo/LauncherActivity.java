package com.example.projectdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.example.projectdemo.mvp.BaseActivity;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

public class LauncherActivity extends BaseActivity {
    private TextView skip;
    private int TIME = 3;
    private boolean isSkip = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        setBar();
        skip = (TextView)findViewById(R.id.skip);

        /*
        将欢迎界面系统自带的标题栏隐藏
        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }
        */

        /*
        设置字符串部分字段为其他颜色
        final SpannableStringBuilder style = new SpannableStringBuilder();
        style.append("关于本活动更多规则，请点我查看");//设置文字

        //设置"请点我查看"文字为红色
        //Spannable. SPAN_INCLUSIVE_EXCLUSIVE：前面包括，后面不包括，即在文本前插入新的文本会应用该样式，而在文本后插入新文本不会应用该样式
        //Spannable. SPAN_INCLUSIVE_INCLUSIVE：前面包括，后面包括，即在文本前插入新的文本会应用该样式，而在文本后插入新文本也会应用该样式
        //Spannable. SPAN_EXCLUSIVE_EXCLUSIVE：前面不包括，后面不包括
        //Spannable. SPAN_EXCLUSIVE_INCLUSIVE：前面不包括，后面包括
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FF0000"));
        style.setSpan(foregroundColorSpan, 11, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        */

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case -2:
                        skip.setText(TIME + "s | 跳过");
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

    @Override
    protected void setBar() {
        ImmersionBar.with(this)
                .hideBar(BarHide.FLAG_HIDE_BAR)// 隐藏状态栏和导航栏
                .init();
    }
}
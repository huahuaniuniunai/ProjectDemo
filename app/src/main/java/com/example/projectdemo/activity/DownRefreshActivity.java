package com.example.projectdemo.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.projectdemo.R;
import com.example.projectdemo.mvp.BaseActivity;
import com.gyf.immersionbar.ImmersionBar;

public class DownRefreshActivity extends BaseActivity {

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_refresh);

        initView();
        initEvent();
    }

    private void initView() {
        swipeRefresh = findViewById(R.id.swipe_refresh);
    }

    private void initData() {
        toast("刷新成功！");
    }

    @SuppressLint("ResourceType")
    private void initEvent() {
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    @Override
    protected void setBar() {
        super.setBar();
        ImmersionBar.with(this)
                .titleBar(R.id.down_refresh)
                .navigationBarColor(R.color.page)
                .init();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DownRefreshActivity.class);
        context.startActivity(intent);
    }
}
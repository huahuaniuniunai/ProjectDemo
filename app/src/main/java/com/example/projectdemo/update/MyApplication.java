package com.example.projectdemo.update;

import android.app.Application;
import android.content.Context;

/**
 * 获取一个全局的context
 */
public class MyApplication extends Application {
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}

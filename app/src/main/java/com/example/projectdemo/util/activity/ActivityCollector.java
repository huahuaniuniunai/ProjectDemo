package com.example.projectdemo.util.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动管理器集合类统一管理activity，随时随地退出程序
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    // 添加一个activity实例到管理器集合类，在onCreate()中调用添加
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    // 从管理器集合类里移除即将销毁的activity实例，在onDestroy()中调用移除
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    // 销毁管理类里所有的activity实例，点击事件调用退出程序
    public static void finish() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
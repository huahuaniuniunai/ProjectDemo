package com.example.projectdemo.util.log;

import android.util.Log;

/**
 * 自定义日志工具
 */
public class LogUtil {
    public static final int VERBOSE = 1;// 开发阶段
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;// 正式上线阶段

    public static final int level = VERBOSE;// 开发阶段将level指定成VERBOSE
//    public static final int level = NOTHING;// 正式上线阶段将level指定成NOTHING
    public static void v(String tag, String msg) {
        if (level <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (level <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (level <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (level <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (level <= ERROR) {
            Log.e(tag, msg);
        }
    }
}

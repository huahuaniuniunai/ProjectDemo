package com.example.projectdemo.util.tool;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * 像素工具类
 */
public class PixelUtil {

    public static int dp2px(Context context, int value) {
        float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (int) ((scale / 160) * value + 0.5f);
    }

    public static float dp2px(Context context, float value) {
        float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (scale / 160) * value + 0.5f;
    }

    public static int px2dp(Context context, int value) {
        float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (int) ((160 / scale) * value + 0.5f);
    }

    public static int px2dp(Context context, float value) {
        float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (int) ((160 / scale) * value + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕宽度（该宽度为可用宽度）
     */
    public static int getScreenWidth(Context context) {
        int width = 0;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point point = new Point();
            display.getSize(point);
            width = point.x;
        } else {
            width = display.getWidth();
        }
        return width;
    }

    /**
     * 获取屏幕高度（该宽度为可用高度）
     */
    public static int getScreenHeight(Context context) {
        int height = 0;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point point = new Point();
            display.getSize(point);
            height = point.y;
        } else {
            height = display.getHeight();
        }
        return height;
    }

    /**
     * 获取屏幕的完整高度
     */
    public static int getScreenRealHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final Display display = windowManager.getDefaultDisplay();
        Point outPoint = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // include navigation bar
            display.getRealSize(outPoint);
        } else {
            // exclude navigation bar
            display.getSize(outPoint);
        }
        return Math.max(outPoint.x, outPoint.y);
    }

    /**
     * 获取屏幕像素密度
     */
    public static float getScreenDensity(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return metrics.densityDpi;
    }

    /**
     * 获取虚拟按键的高度
     */
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        if (hasNavBar(context)) {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    /**
     * 检查是否存在虚拟按键栏
     */
    public static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else {
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            }
        }
        return sNavBarOverride;
    }

}

package com.example.projectdemo.util.statusbar;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.core.content.ContextCompat;

import com.example.projectdemo.R;
import com.jaeger.library.StatusBarUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class StatusBarHelper {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({VZStatusBarType.BAR_TYPE_NO, VZStatusBarType.BAR_TYPE_HOME, VZStatusBarType.BAR_TYPE_COLOR, VZStatusBarType.BAR_TYPE_IMAGE})
    public @interface VZStatusBarType {
        int BAR_TYPE_NO = 0;      // 默认纯色背景
        int BAR_TYPE_HOME = 1;    // 首页
        int BAR_TYPE_COLOR = 2;   // 标题栏纯色背景 默认白色
        int BAR_TYPE_IMAGE = 3;   // 标题栏图片背景
    }

    /**
     * 初始化栏态栏
     *
     * @param activity 当前页面上下文
     * @param barType  BAR_TYPE_NO:状态栏不改变, BAR_TYPE_HOME首页, BAR_TYPE_COLOR白色, BAR_TYPE_IMAGE图片
     */
    public static void setStatusBar(Activity activity, @VZStatusBarType int barType) {
        setStatusBar(activity, barType, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA, true);
    }

    /**
     * 初始化栏态栏
     *
     * @param activity       当前页面上下文
     * @param barType        BAR_TYPE_NO:状态栏不改变, BAR_TYPE_HOME首页, BAR_TYPE_COLOR白色, BAR_TYPE_IMAGE图片
     * @param lightStatusBar 状态栏文字颜色 true文字颜色深色  false文字颜色浅色
     */
    public static void setStatusBar(Activity activity, @VZStatusBarType int barType, boolean lightStatusBar) {
        setStatusBar(activity, barType, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA, lightStatusBar);
    }

    /**
     * 初始化栏态栏
     *
     * @param activity       当前页面上下文
     * @param barType        BAR_TYPE_NO:状态栏不改变, BAR_TYPE_HOME首页, BAR_TYPE_COLOR纯色, BAR_TYPE_IMAGE图片
     * @param statusBarAlpha 状态栏透明度 0-255
     */
    public static void setStatusBar(Activity activity, @VZStatusBarType int barType, int statusBarAlpha) {
        setStatusBar(activity, barType, ContextCompat.getColor(activity, R.color.white), statusBarAlpha, true, null);
    }

    /**
     * 初始化栏态栏
     *
     * @param activity       当前页面上下文
     * @param barType        BAR_TYPE_NO:状态栏不改变, BAR_TYPE_HOME首页, BAR_TYPE_COLOR纯色, BAR_TYPE_IMAGE图片
     * @param statusBarAlpha 状态栏透明度 0-255
     * @param lightStatusBar 状态栏文字颜色 true文字颜色深色  false文字颜色浅色
     */
    public static void setStatusBar(Activity activity, @VZStatusBarType int barType, int statusBarAlpha, boolean lightStatusBar) {
        setStatusBar(activity, barType, ContextCompat.getColor(activity, R.color.white), statusBarAlpha, lightStatusBar, null);
    }

    /**
     * 初始化栏态栏
     *
     * @param activity       当前页面上下文
     * @param barType        BAR_TYPE_NO:状态栏不改变, BAR_TYPE_HOME首页, BAR_TYPE_COLOR纯色, BAR_TYPE_IMAGE图片
     * @param barColor       状态栏背景色
     * @param statusBarAlpha 状态栏透明度 0-255
     * @param lightStatusBar 状态栏文字颜色 true文字颜色深色  false文字颜色浅色
     */
    public static void setStatusBar(Activity activity, @VZStatusBarType int barType, @ColorInt int barColor, int statusBarAlpha, boolean lightStatusBar) {
        setStatusBar(activity, barType, barColor, statusBarAlpha, lightStatusBar, null);
    }

    /**
     * 初始化栏态栏
     *
     * @param activity       当前页面上下文
     * @param barType        BAR_TYPE_NO:状态栏不改变, BAR_TYPE_HOME首页, BAR_TYPE_COLOR纯色, BAR_TYPE_IMAGE图片
     * @param barColor       状态栏背景色
     * @param statusBarAlpha 状态栏透明度 0-255
     * @param lightStatusBar 状态栏文字颜色 true文字颜色深色  false文字颜色浅色
     * @param needOffsetView 需要处理位移的View 在图片背景类型下，可以指定View处理布局内容显示到状态栏里面的问题
     */
    public static void setStatusBar(Activity activity, @VZStatusBarType int barType, @ColorInt int barColor, int statusBarAlpha, boolean lightStatusBar, View needOffsetView) {
        if (barType == VZStatusBarType.BAR_TYPE_NO) { //状态栏不改变
            //什么也不做
        } else if (barType == VZStatusBarType.BAR_TYPE_HOME) {
            setStatusBarHome(activity);
        } else if (barType == VZStatusBarType.BAR_TYPE_COLOR) {
            setStatusBarColor(activity, barColor, lightStatusBar, statusBarAlpha);
        } else if (barType == VZStatusBarType.BAR_TYPE_IMAGE) {
            setStatusBarImage(activity, statusBarAlpha, lightStatusBar, needOffsetView);
        }
    }

    /**
     * 初始化首页VZHomeActivity的状态栏
     *
     * @param activity 当前页面上下文
     */
    private static void setStatusBarHome(Activity activity) {
        com.jaeger.library.StatusBarUtil.setTransparentForImageViewInFragment(activity, null);
    }

    /**
     * 初始化纯色背景的状态栏
     *
     * @param activity       当前页面上下文
     * @param barColor       状态栏背景色
     * @param statusBarAlpha 状态栏透明度 0-255
     */
    private static void setStatusBarColor(Activity activity, @ColorInt int barColor, boolean lightStatusBar, int statusBarAlpha) {
        if (isSupportStatusBarFontColor()) {
            statusBarAlpha = 0;
        }
        com.jaeger.library.StatusBarUtil.setColorForSwipeBack(activity, barColor, statusBarAlpha);
        setLightStatusBar(activity, lightStatusBar);
    }

    /**
     * 初始化图片背景的状态栏
     *
     * @param activity       当前页面上下文
     * @param statusBarAlpha 状态栏透明度 0-255
     * @param needOffsetView 需要处理位移的View
     */
    private static void setStatusBarImage(Activity activity, int statusBarAlpha, boolean lightStatusBar, View needOffsetView) {
        if (isSupportStatusBarFontColor()) {
            statusBarAlpha = 0;
        }
        com.jaeger.library.StatusBarUtil.setTranslucentForImageView(activity, statusBarAlpha, needOffsetView);

        setLightStatusBar(activity, lightStatusBar);
    }

    /**
     * 设置栏态栏文字颜色风格
     *
     * @param activity       当前页面上下文
     * @param lightStatusBar 状态栏文字颜色 true文字颜色深色  false文字颜色浅色
     */
    public static void setLightStatusBar(Activity activity, boolean lightStatusBar) {
        if (isSupportStatusBarFontColor()) {
            LightStatusBarCompat.setLightStatusBar(activity.getWindow(), lightStatusBar);
        } else {
            LightStatusBarCompat.setLightStatusBar(activity.getWindow(), false);
        }
    }

    /**
     * 判断手机状态栏是否支持设置为纯透明
     *
     * @return true是 false否
     */
    private static boolean isSupportStatusBarFontColor() {
        return RomUtil.isSmartisan() || RomUtil.isMiui() || RomUtil.isFlyme() || Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 获取手机状态栏的高度
     *
     * @param context 上下文
     * @return 手机状态栏的高度
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return statusBarHeight;
        }

        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId);
        }
        if (statusBarHeight <= 0) {
            statusBarHeight = resources.getDimensionPixelSize(R.dimen.statusbar_view_height);
        }
        return statusBarHeight;
    }

}

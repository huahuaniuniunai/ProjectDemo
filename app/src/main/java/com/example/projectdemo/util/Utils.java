package com.example.projectdemo.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.canyinghao.canrefresh.CanRefreshLayout;
import com.example.projectdemo.application.MyApplication;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zd on 2017/5/24.
 */

public class Utils {

    private static String versionName;

    /**
     * 通用回调函数接口
     */
    public static interface Callback {
        public Object onCallback(Object args);
    }

    private static int versioncode;

    /**
     * 打开一个Activity
     */
    public static void startActivity(Context context, Class<?> cls, String[] extra) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (extra != null) {
            for (int i = 0; i < extra.length - 1; i += 2)
                intent.putExtra(extra[i], extra[i + 1]);
        }
        context.startActivity(intent);
    }


    /**
     * 返回当前程序版本号
     */
    public static int getAppVersionName(Context context) {

        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            //versionName = pi.versionName;
            versioncode = pi.versionCode;

        } catch (Exception e) {
            Log.e("CarNet", "Exception", e);
        }
        return versioncode;
    }

    /**
     * 返回当前程序版本名称
     */
    public static String getAppVersion(Context context) {

        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;

        } catch (Exception e) {
            Log.e("CarNet", "Exception", e);
        }
        return versionName;
    }

    public static void getDisplay(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Log.e("TTTT", "height:" + size.y);
        Log.e("TTTT", "width:" + size.x);
    }

    public static String encodeBase64(String str) {

        byte[] result = Base64.decode(str.toString(), Base64.DEFAULT);

        return new String(result);


    }


    /**
     * 32位MD5加密方法
     * 16位小写加密只需getMd5Value("xxx").substring(8, 24);即可
     *
     * @param sSecret
     * @return
     */
    public static String getMd5Value(String sSecret) {
        try {
            MessageDigest bmd5 = MessageDigest.getInstance("MD5");
            bmd5.update(sSecret.getBytes());
            int i;
            StringBuffer buf = new StringBuffer();
            byte[] b = bmd5.digest();// 加密
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 弹出确认对话框
     */
    public static void confirm(Context context, String text, String title, final Callback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title == null ? "确认信息" : title);
        builder.setMessage(text);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (callback != null)
                    callback.onCallback(true);
            }
        });
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (callback != null)
                    callback.onCallback(false);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (callback != null)
                    callback.onCallback(false);
            }
        });
        builder.create().show();
    }

    //拼出url地址
    public static String getUrl(String url, HashMap params) {
        Iterator i = params.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry entry = (Map.Entry) i.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            url = url + "&" + key + "=" + value;
        }
        url = url.replaceFirst("&", "?");

        return url;
    }


    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }

    public static String getYYDDMMSS(){
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd HH:mm");
        String date=sdf.format(new java.util.Date());
        return  date;

    }

    public static void showNoNetWork(Context mContext, CanRefreshLayout refresh, Boolean loadmore){
        if(!MyApplication.isNetworkAvailable(mContext)){//没网
            Toast.makeText(mContext,"网络异常，请检查网络", Toast.LENGTH_SHORT).show();
            if(loadmore){
                refresh.loadMoreComplete();
            }else{
                refresh.refreshComplete();
                 }

            }
    }

//    public static void showNoNetWork(Context mContext){
//        if(!App.isNetworkAvailable(mContext)){//没网
//            Toast.makeText(mContext,"网络异常，请检查网络", Toast.LENGTH_SHORT).show();
//
//        }
//    }

}
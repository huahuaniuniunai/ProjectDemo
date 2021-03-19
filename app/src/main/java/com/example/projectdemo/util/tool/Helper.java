package com.example.projectdemo.util.tool;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;


public class Helper {
    /**
     * 给ImageView添加图层叠色
     */
    public static void setImageTint(ImageView iv, int color) {
        iv.setColorFilter(color);
    }

    /**
     * 获取安全字符串
     */
    public static String getSaleString(String str, String def) {
        if (TextUtils.isEmpty(str)) {
            return def;
        }
        return str;
    }

    /**
     * 获取安全字符串
     * 如果为空为null,则默认--
     */
    public static String getSaleString(String str) {
        return getSaleString(str, "--");
    }

    /**
     * 获取安全字符串
     */
    public static String getSaleString2(String str) {
        return getSaleString(str, "");
    }

    /**
     * 判断非空list
     */
    public static boolean isListValid(List list) {
        return list != null && !list.isEmpty();
    }

    /**
     * 判断非空map
     */
    public static boolean isMapValid(Map map) {
        return map != null && !map.isEmpty();
    }

    /**
     * 创建html字体
     */
    public static String makeHtmlText(String text, String color) {
        return String.format("<font color=\"%s\">%s</font>", color, text);
    }

    /**
     * textView 设置html
     */
    public static void fromHtml(TextView tv, String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv.setText(Html.fromHtml(text));
        }
    }

//    /**
//     * 对话框
//     */
//    public static MsgDialog showMsgDialog(Context context, String msg) {
//        MsgDialog msgDialog = new MsgDialog(context);
//        msgDialog.setMsg(msg);
//        msgDialog.getMsgView().setGravity(Gravity.CENTER);
//        msgDialog.setOk("我知道了");
//        msgDialog.setCancel(null);
//        msgDialog.show();
//        return msgDialog;
//    }

    /**
     * 字符串转Long
     */
    public static long strToLong(String s) {
        if (TextUtils.isEmpty(s)) {
            return 0;
        }
        long l = 0;
        try {
            l = Long.parseLong(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            l = 0;
        }
        return l;
    }


    /**
     * 调用系统发送短信
     */
    public static void sendSMS(Context context, String mobile, String smsBody) {
        Uri smsToUri = Uri.parse(String.format("smsto:%s", mobile));
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", smsBody);
        context.startActivity(intent);
    }
}
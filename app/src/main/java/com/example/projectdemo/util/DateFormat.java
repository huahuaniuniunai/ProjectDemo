package com.example.projectdemo.util;

import java.util.Calendar;
import java.util.TimeZone;

public class DateFormat {
    //获得当前年月日时分秒星期
    public static String getTime(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        String mHour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));//时
        String mMinute = String.valueOf(c.get(Calendar.MINUTE));//分
        String mSecond = String.valueOf(c.get(Calendar.SECOND));//秒

        if (mMinute.length() != 2) {
            mMinute = "0" + mMinute;
        }

        if (mSecond.length() != 2) {
            mSecond = "0" + mSecond;
        }

        switch (mWay) {
            case "1":
                mWay = "天";
                break;
            case "2":
                mWay = "一";
                break;
            case "3":
                mWay = "二";
                break;
            case "4":
                mWay = "三";
                break;
            case "5":
                mWay = "四";
                break;
            case "6":
                mWay = "五";
                break;
            case "7":
                mWay = "六";
                break;
        }

        return mYear + "年" + mMonth + "月" + mDay + "日" + "\n"
                + "星期" + mWay + "  " + mHour + ":" + mMinute + ":" + mSecond;
    }
}

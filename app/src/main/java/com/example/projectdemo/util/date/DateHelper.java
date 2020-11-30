package com.example.projectdemo.util.date;

import android.annotation.SuppressLint;

import com.example.projectdemo.util.Helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DateHelper {

    @SuppressLint("SimpleDateFormat")
    public static String millToTime(long mill, String pattern, String def) {
        if (mill <= 0) {
            return def;
        }
        String time;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(mill);
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setTimeZone(cal.getTimeZone());
            time = sdf.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            time = null;
        }
        return Helper.getSaleString(time, def);
    }

    public static String millToHS(long mill) {
        String result = "";
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(mill);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            sdf.setTimeZone(cal.getTimeZone());
            String hs = sdf.format(cal.getTime());
            int apm = cal.get(Calendar.AM_PM);
            result = String.format("%s%s", (apm > 0 ? "下午" : "上午"), hs);
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }

    /**
     * 获取星期
     * 今天、明天、后天、周*
     */
    public static String getWeek(Calendar calendar, String today, String tomorrow, String dayAfterTomorrow) {
        Calendar cal = Calendar.getInstance(calendar.getTimeZone());
        if (isSameDay(calendar, cal)) {
            return today;
        }
        cal.add(Calendar.DAY_OF_MONTH, 1);
        if (isSameDay(calendar, cal)) {
            return tomorrow;
        }

        cal.add(Calendar.DAY_OF_MONTH, 1);
        if (isSameDay(calendar, cal)) {
            return dayAfterTomorrow;
        }
        return getWeek(calendar);
    }


    /**
     * 是否为同一天
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            return false;
        }
        return toZeroCal(cal1).compareTo(toZeroCal(cal2)) == 0;
    }

    /**
     * 清除calendar的时分秒毫秒
     */
    public static Calendar toZeroCal(Calendar cal) {
        if (cal != null) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        }
        return cal;
    }

    /**
     * 获取星期
     * 周*
     */
    public static String getWeek(Calendar calendar) {
        if (calendar == null) {
            return "";
        }
        String[] weeks = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        int weekInt = calendar.get(Calendar.DAY_OF_WEEK);
        return weeks[weekInt - 1];
    }

    /**
     * 获取聊天页面时间展示
     */
    public static String getTimeDescFromChar(long time) {
        Calendar timeCal = Calendar.getInstance();
        timeCal.setTimeInMillis(time);

        Calendar cal = Calendar.getInstance();
        if (DateHelper.isSameDay(timeCal, cal)) {
            return DateHelper.millToTime(time, "HH:mm", "");
        }
        cal.add(Calendar.DAY_OF_MONTH, -1);
        if (DateHelper.isSameDay(timeCal, cal)) {
            return String.format("昨天%s", DateHelper.millToTime(time, "HH:mm", ""));
        }
        return DateHelper.millToTime(time, "MM月dd日 HH:mm", "");
    }

}

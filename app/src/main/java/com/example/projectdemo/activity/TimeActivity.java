package com.example.projectdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.projectdemo.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeActivity extends AppCompatActivity {

    TextView mTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        mTime = findViewById(R.id.chat_time);
        String time = getTimeShowString(System.currentTimeMillis(), false);
        mTime.setText(time);
    }

    public static String getTimeShowString(long milliseconds, boolean abbreviate) {
        String dataString;
        String timeStringBy24;

        Date currentTime = new Date(milliseconds);
        Date today = new Date();
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        Date todaybegin = todayStart.getTime();
        Date yesterdaybegin = new Date(todaybegin.getTime() - 3600 * 24 * 1000);
        Date preyesterday = new Date(yesterdaybegin.getTime() - 3600 * 24 * 1000);

        if (!currentTime.before(todaybegin)) {
            dataString = "今天";
        } else if (!currentTime.before(yesterdaybegin)) {
            dataString = "昨天";
        } else if (!currentTime.before(preyesterday)) {
            dataString = "前天";
        } else if (isSameWeekDates(currentTime, today)) {
            dataString = getWeekOfDate(currentTime);
        } else {
            SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            dataString = dateformatter.format(currentTime);
        }

        SimpleDateFormat timeformatter24 = new SimpleDateFormat("HH:mm", Locale.getDefault());
        timeStringBy24 = timeformatter24.format(currentTime);

        if (abbreviate) {
            if (!currentTime.before(todaybegin)) {
                return getTodayTimeBucket(currentTime);
            } else {
                return dataString;
            }
        } else {
            return dataString + " " + timeStringBy24;
        }
    }

    /**
     * 根据不同时间段，显示不同时间
     *
     * @param date
     * @return
     */
    public static String getTodayTimeBucket(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat timeformatter0to11 = new SimpleDateFormat("KK:mm", Locale.getDefault());
        SimpleDateFormat timeformatter1to12 = new SimpleDateFormat("hh:mm", Locale.getDefault());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 0 && hour < 5) {
            return "凌晨 " + timeformatter0to11.format(date);
        } else if (hour >= 5 && hour < 12) {
            return "上午 " + timeformatter0to11.format(date);
        } else if (hour >= 12 && hour < 18) {
            return "下午 " + timeformatter1to12.format(date);
        } else if (hour >= 18 && hour < 24) {
            return "晚上 " + timeformatter1to12.format(date);
        }
        return "";
    }

    /**
     * 根据日期获得星期
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        // String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    /**
     * 判断两个日期是否在同一周
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            return cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR);
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            return cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR);
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            return cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR);
        }
        return false;
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TimeActivity.class);
        context.startActivity(intent);
    }

}
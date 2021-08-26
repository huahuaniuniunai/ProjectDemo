package com.example.projectdemo.util.date;

import com.example.projectdemo.util.tool.StringUtil;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;


public class DateUtil {

    public static String oraDateFormat = "TO_DATE(?, 'yyyy-MM-dd')";
    public static String oraTimeFormat = "TO_DATE(?, 'yyyy-MM-dd HH24:mi:ss')";
    static public SimpleDateFormat MMddYYYY_HHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static public SimpleDateFormat HHmmss = new SimpleDateFormat("HH:mm:ss");
    static public SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
    static public SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    static public SimpleDateFormat yyyyMMdd8 = new SimpleDateFormat("yyyyMMdd");
    static public SimpleDateFormat yyyyMM= new SimpleDateFormat("yyyyMM");
    static public SimpleDateFormat yyyyMMddE = new SimpleDateFormat("yyyy年MM月dd日E");
    static public SimpleDateFormat yyyyMMddStr = new SimpleDateFormat("yyyy年MM月dd日");
    static public SimpleDateFormat yyyyMMddEStr = new SimpleDateFormat("yyyy-MM-dd E");


    /**
     * 得到当前时间的年月
     *
     * @param day
     */
    public static String getYearMonth(String day) {
        if (day == null) return "";
        if (day.length() < 8) return "";
        int n = day.lastIndexOf("-");
        return day.substring(0, n);
    }

    /**
     * 得到当前时间的yyyy年 mm月
     *
     * @param day
     */
    public static String getYearMonthStr(String day) {
        if (day == null) return "";
        if (day.length() < 8) return "";
        return day.substring(0, 4) + "年　" + day.substring(5, 7) + "月";
    }

    /**
     * 得到当前时间的yyyy年 mm月dd日
     *
     * @param day
     */
    public static String getYearMonthDayStr(String day) {
        if (day == null) return "";
        if (day.length() < 8) return "";
        return day.substring(0, 4) + "年　" + day.substring(5, 7) + "月" + day.substring(8) + "日";
    }

    /**
     * 返回指定时间的年
     *
     * @param day
     */
    public static int getYear(String day) {
        if (day == null) return 0;
        if (day.length() < 8) return 0;
        return Integer.parseInt(day.substring(0, 4));
    }

    /**
     * 返回指定时间的月
     *
     * @param day
     */
    public static String getMonth(String day) {
        if (day == null) return "0";
        if (day.length() < 8) return "0";
        int m = day.indexOf("-");
        int n = day.lastIndexOf("-");
        String temp = day.substring(m + 1, n);
        if (temp.length() == 1) temp = "0" + temp;
        return temp;
    }

    /**
     * 返回指定时间的日
     *
     * @param day
     */
    public static String getDate(String day) {
        if (day == null) return "0";
        if (day.length() < 8) return "0";
        int n = day.lastIndexOf("-");
        String temp = day.substring(n + 1);
        if (temp.length() == 1) temp = "0" + temp;
        return temp;
    }

    /**
     * 将时间转化为yyyy-MM-dd HH:mm:ss的字符串时间
     *
     * @param date
     */
    public static String date2str(java.util.Date date) {
        if (date == null) return "";
        try {
            return MMddYYYY_HHmmss.format(date);
        }
        catch (Exception e) {
            return "";
        }
    }


    /**
     * 将时间转化为HH:mm:ss的字符串时间
     */
    public static String getcurHMS() {
        try {
            return HHmmss.format(new java.util.Date());
        }
        catch (Exception e) {
            return "";
        }
    }

    /**
     * 返回当前时间
     */
    public static String getCurDateTime() {
        try {
            return MMddYYYY_HHmmss.format(new java.util.Date());
        }
        catch (Exception e) {
            return "";
        }
    }

    /**
     * 将时间转化为yyyy年MM月dd日E的字符串时间
     *
     * @param date
     */
    public static String date2YMDE(java.util.Date date) {
        if (date == null) return "";
        try {
            return yyyyMMddE.format(date);
        }
        catch (Exception e) {
            return "";
        }
    }

    /**
     * 将时间转化为yyyy-MM-dd E的字符串时间
     *
     * @param date
     */
    public static String date2YMDEStr(java.util.Date date) {
        if (date == null) return "";
        try {
            return yyyyMMddEStr.format(date);
        }
        catch (Exception e) {
            return "";
        }
    }

    /**
     * 将时间转化为yyyy-MM-dd的字符串时间
     *
     * @param date
     */
    public static String getDateStr(java.util.Date date) {
        if (date == null) return "";
        try {
            return yyyyMMdd.format(date);
        }
        catch (Exception e) {
            return "";
        }
    }

    /**
     * 将时间转化为yyyyMMdd的字符串时间
     *
     * @param date
     */
    public static String getDateStr8(java.util.Date date) {
        if (date == null) return "";
        try {
            return yyyyMMdd8.format(date);
        }
        catch (Exception e) {
            return "";
        }
    }

    /**
     * 将sql时间转化为yyyy-MM-dd的字符串时间
     *
     * @param date
     */
    static public String date2str(Date date) {
        if (date == null) return "";
        try {
            return yyyyMMdd.format(date);
        }
        catch (Exception e) {
            return "";
        }

    }

    /**
     * 将sql时间转化为yyyy-MM-dd HH:mm:ss的字符串时间
     *
     * @param date
     */
    static public String dateTime2str(Date date) {
        if (date == null) return "";
        try {
            return MMddYYYY_HHmmss.format(date);
        }
        catch (Exception e) {
            return "";
        }
    }

    /**
     * 将sql时间转化为yyyy-MM-dd HH:mm:ss的字符串时间
     *
     * @param date
     */
    static public String dateTime2str(java.sql.Timestamp date) {
        if (date == null) return "";
        try {
            return MMddYYYY_HHmmss.format(date);
        }
        catch (Exception e) {
            return "";
        }
    }

    /**
     * 只得到将时间的年份格式为YYYY
     *
     * @param date
     */
    static public String getYYYY(java.util.Date date) {
        if (date == null) return "";
        return yyyy.format(date);
    }

    /**
     * 将字符串时间 YYYY-MM-DD 转化为 java.sql.Date object
     *
     * @param str
     */
    public static Date str2date(String str) {
        Date result = null;
        try {
            java.util.Date udate = yyyyMMdd.parse(str);
            result = new Date(udate.getTime());
            return result;
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过当前时间得到下星期的日期，即这星期几得到下星期几是多少号
     *
     * @param curday
     */
    public static String getNexWeekDayByStr(String curday) {
        try {
            java.util.Date date = str2date(curday);
/*            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(date);
            int week = rightNow.get(Calendar.DAY_OF_WEEK);
            if (week == 7) week = 6;
            if (week == 1) week = 0;*/
            return getDateStr(new java.util.Date(date.getTime() + 7 * 24 * 3600 * 1000));
        }
        catch (Exception e) {
            return "";
        }
    }

    /**
     * 通过当前时间得到上星期的日期，即这星期几得到上星期几多少号
     *
     * @param curday
     */
    public static String getPreWeekDayByStr(String curday) {
        try {
            java.util.Date date = str2date(curday);
/*            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(date);
            int week = rightNow.get(Calendar.DAY_OF_WEEK);
            if (week == 0) week = 1;
            if (week == 7) week = 8;*/
            return getDateStr(new java.util.Date(date.getTime() - 7 * 24 * 3600 * 1000));
        }
        catch (Exception e) {
            return "";
        }
    }

    /**
     * 得到当前日期是本月第几周
     *
     * @param curday
     */
    public static int getMonthWeek(String curday) {
        try {
            java.util.Date date = str2date(curday);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(date);
            return rightNow.get(Calendar.WEEK_OF_MONTH);
        }
        catch (Exception e) {
            return 0;
        }
    }

    /**
     * 得到当前日期是本年第几周
     *
     * @param curday
     */
    public static int getYearWeek(String curday) {
        try {
            java.util.Date date = str2date(curday);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(date);
            return rightNow.get(Calendar.WEEK_OF_YEAR);
        }
        catch (Exception e) {
            return 0;
        }
    }

    /**
     * 通过当前日期得到上个月的日期
     *
     * @param curday df
     */
    public static String getPreMonthDayStr(String curday) {
        int year = getYear(curday);
        String monthStr = getMonth(curday);
        int month = Integer.parseInt(monthStr);
        if (month <= 1) {
            month = 12;
            year = year - 1;
        } else month = month - 1;
        monthStr = String.valueOf(month);
        if (monthStr.length() == 1) monthStr = "0" + monthStr;
        return year + "-" + monthStr + "-01";

    }

    /**
     * 通过当前日期得到下个月的日期
     *
     * @param curday
     */
    public static String getNextMonthDayStr(String curday) {
        int year = getYear(curday);
        String monthStr = getMonth(curday);
        int month = Integer.parseInt(monthStr);
        if (month >= 12) {
            month = 1;
            year = year + 1;
        } else month = month + 1;
        monthStr = String.valueOf(month);
        if (monthStr.length() == 1) monthStr = "0" + monthStr;
        return year + "-" + monthStr + "-01";
    }

    /**
     * 得到当前日期的上一天的日期
     *
     * @param curday
     */
    public static String getPreDayStr(String curday) {
        java.util.Date date = str2date(curday);
        return date2str(new Date(date.getTime() - 24 * 3600 * 1000));

    }

    /**
     * 得到当前日期的下一天的日期
     *
     * @param curday
     */
    public static String getNextDayStr(String curday) {
        java.util.Date date = str2date(curday);
        return date2str(new Date(date.getTime() + 24 * 3600 * 1000));
    }

    /**
     * 通过字符串日期得到它的年月日期为一号
     *
     * @param curday
     */
    public static String getCurMonthDayStr(String curday) {
        int year = getYear(curday);
        String monthStr = getMonth(curday);
        int month = Integer.parseInt(monthStr);
        monthStr = String.valueOf(month);
        if (monthStr.length() == 1) monthStr = "0" + monthStr;
        return year + "-" + monthStr + "-01";
    }

    /**
     * 通过字符中日期得到该日期是当前星期中的星期几
     *
     * @param curday
     */
    public static int getCurWeekDayByStr(String curday) {
        try {
            java.util.Date date = str2date(curday);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(date);
            return rightNow.get(Calendar.DAY_OF_WEEK);
        }
        catch (Exception e) {
            return 1;
        }
    }

    /**
     * 通过字符中日期得到该日期是当前星期中的星期几
     *
     * @param curday
     */
    public static int getCurWeekDayByStr2(String curday) {
        try {
            java.util.Date date = str2date(curday);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(date);
            return rightNow.get(Calendar.DAY_OF_WEEK) == 1 ? 7 : rightNow.get(Calendar.DAY_OF_WEEK) - 1;
        }
        catch (Exception e) {
            return 1;
        }
    }

    /**
     * 通过字符串日期得到当前星期的所有日期
     *
     * @param curday
     */
    public static String[] getallweekdate(String curday) {
        String[] pandy = new String[7];
        int day = getCurWeekDayByStr(curday) - 1;
        String firstday = getAllowPreDay(curday, day);
        for (int i = 0; i < 7; i++) {
            pandy[i] = firstday;
            firstday = getNextDayStr(firstday);
        }
        return pandy;
    }

    /**
     * 通过字符串日期得到当前星期的所有日期
     *
     * @param curday
     */
    public static String[] getallweekdateCn(String curday) {
        String[] pandy = new String[7];
        int day = getCurWeekDayByStr(curday) - 1;
        String firstday = getAllowPreDay(curday, day);
        for (int i = 0; i < 7; i++) {
            firstday = getNextDayStr(firstday);
            pandy[i] = firstday;
        }
        return pandy;
    }

    /**
     * 通过开始日期和结束日期得到其之间的所有星期的第一天和最后一天的日期
     *
     * @param startdate
     * @param enddate
     */
    public static String[][] getweekfl(String startdate, String enddate) {
        java.util.Date date1 = str2date(startdate);
        java.util.Date date2 = str2date(enddate);
        long weekcount = (date2.getTime() - date1.getTime()) / (24 * 3600 * 1000 * 7) + 1;
        int weekc = (int) weekcount;
        if (getCurWeekDayByStr(startdate) > getCurWeekDayByStr(enddate)) {
            weekc++;
        }
        String[][] pandy = new String[weekc][2];
        String tempdate = startdate;
        for (int i = 0; i < weekc; i++) {
            pandy[i][0] = getallweekdate(tempdate)[0];
            pandy[i][1] = getallweekdate(tempdate)[6];
            tempdate = getNextDayStr(pandy[i][1]);
        }
        return pandy;
    }

    /**
     * 通过开始日期和结束日期得到其之间的所有星期的第一天和最后一天的日期
     *
     * @param startdate
     * @param enddate
     */
    public static String[][] getweekfl1(String startdate, String enddate) {
        java.util.Date date1 = str2date(startdate);
        java.util.Date date2 = str2date(enddate);
        long weekcount = (date2.getTime() - date1.getTime()) / (24 * 3600 * 1000 * 7);
        int weekc = (int) weekcount;
        if (getCurWeekDayByStr(startdate) > getCurWeekDayByStr(enddate)) {
            weekc++;
        }
        String[][] pandy = new String[weekc][2];
        String tempdate = startdate;
        for (int i = 0; i < weekc; i++) {
            pandy[i][0] = getallweekdate(tempdate)[0];
            if (i == 0)
                pandy[i][0] = startdate;
            pandy[i][1] = getallweekdate(tempdate)[6];
            tempdate = getNextDayStr(pandy[i][1]);
        }
        return pandy;
    }

    /**
     * 通过开始日期和结束日期得到之间所有月的第一天和最后一天的日期
     *
     * @param startdate
     * @param enddate
     */
    public static Vector getmonthfl(String startdate, String enddate) {
        Vector pandy = new Vector();
        String tempdate = getCurMonthDayStr(startdate);
        while (tempdate.compareTo(enddate) <= 0) {
            String[] pan = new String[2];
            pan[0] = tempdate;
            pan[1] = getPreDayStr(getNextMonthDayStr(tempdate));
            pandy.add(pan);
            tempdate = getNextMonthDayStr(tempdate);
        }
        return pandy;
    }

    /**
     * 通过开始日期和结束日期得到之间所有月的第一天和最后一天的日期
     *
     * @param startdate
     * @param enddate
     */
    public static Vector getmonthfl1(String startdate, String enddate) {
        Vector pandy = new Vector();
        String tempdate = getCurMonthDayStr(startdate);
        String[] temp = new String[2];
        temp[0] = startdate;
        temp[1] = getPreDayStr(getNextMonthDayStr(tempdate));
        if(startdate.substring(0,7).compareTo(DateUtil.getToday().substring(0,7)) <0)
            pandy.add(temp);
        while (getNextMonthDayStr(tempdate).compareTo(enddate) <= 0 && !getNextMonthDayStr(tempdate).equals(getCurMonthDayStr(enddate)))
        {
            String[] pan = new String[2];
            tempdate = getNextMonthDayStr(tempdate);
            pan[0] = tempdate;
            pan[1] = getPreDayStr(getNextMonthDayStr(tempdate));
            pandy.add(pan);
        }
        return pandy;
    }

    /**
     * 通过开始日期和结束日期得到它们之间的所有日期并用,分开
     *
     * @param startdate
     * @param enddate
     */
    public static String getMulday(String startdate, String enddate) {
        String pandy = "";
        String tempdate = startdate;
        while (tempdate.compareTo(enddate) <= 0) {
            pandy += tempdate + ",";
            tempdate = getNextDayStr(tempdate);
        }
        if (pandy.length() > 0)
            pandy = pandy.substring(0, pandy.length() - 1);
        return pandy;
    }

    /**
     * 通过开始日期和结束日期得到这们之间所有星期的第一天和最后一天并用,分开
     *
     * @param startdate
     * @param enddate
     */
    public static String getMulweekday(String startdate, String enddate) {
        String pandy = "";
        String[][] tempdate = getweekfl(startdate, enddate);
        for (int i = 0; i < tempdate.length; i++) {
            pandy += tempdate[i][0] + "--" + tempdate[i][1] + ",";
        }
        if (pandy.length() > 0)
            pandy = pandy.substring(0, pandy.length() - 1);
        return pandy;
    }

    /**
     * 通过开始日期和结束日期得到它们之间所有月的第一天和最后一天日期用,分开
     *
     * @param startdate
     * @param enddate
     */
    public static String getMulmonthday(String startdate, String enddate) {
        String pandy = "";
        Vector tempdate = getmonthfl(startdate, enddate);
        String[] temp = new String[2];
        for (int i = 0; i < tempdate.size(); i++) {
            temp = (String[]) tempdate.get(i);
            pandy += temp[0] + "--" + temp[1] + ",";

        }
        if (pandy.length() > 0)
            pandy = pandy.substring(0, pandy.length() - 1);
        return pandy;
    }

    /**
     * 通过开始时间得到count后的日期
     *
     * @param startdate
     * @param count
     */
    public static String getAllowDay(String startdate, int count) {
        String pandy = startdate;
        for (int i = 0; i < count; i++) {
            pandy = getNextDayStr(pandy);
        }
        return pandy;
    }

    /**
     * 通过开始时间得到count前的日期
     *
     * @param startdate
     * @param count
     */
    public static String getAllowPreDay(String startdate, int count) {
        String pandy = startdate;
        for (int i = 0; i < count; i++) {
            pandy = getPreDayStr(pandy);
        }
        return pandy;
    }

    /**
     * 通过开始时间得到count星期后的日期
     *
     * @param startdate
     * @param count
     */
    public static String getAllowWeek(String startdate, int count) {
        return getAllowDay(startdate, count * 7);
    }

    /**
     * 通过开始时间得到count星期前的日期
     *
     * @param startdate
     * @param count
     */
    public static String getAllowPreWeek(String startdate, int count) {
        return getAllowPreDay(startdate, count * 7);
    }

    /**
     * 通过开始时间得到count月后的日期
     *
     * @param startdate
     * @param count
     */
    public static String getAllowMonth(String startdate, int count) {
        String tempdate = getCurMonthDayStr(startdate);
        for (int i = 0; i < count; i++) {
            tempdate = getNextMonthDayStr(tempdate);
        }
        return tempdate;
    }

    /**
     * 通过开始时间得到count月前的日期
     *
     * @param startdate
     * @param count
     */
    public static String getAllowPreMonth(String startdate, int count) {
        String tempdate = getCurMonthDayStr(startdate);
        for (int i = 0; i < count; i++) {
            tempdate = getPreMonthDayStr(tempdate);
        }
        return tempdate;
    }

    /**
     * 得到一个月有多少天
     */
    public static int getMonthDaysCounts(String curday) {
        long daycount = 0;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(str2date(getCurMonthDayStr(curday)));
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(str2date(getNextMonthDayStr(curday)));
            daycount = (calendar2.getTimeInMillis() - calendar.getTimeInMillis()) / (1000 * 60 * 60 * 24);
            return (int) daycount;
        }
        catch (Exception e) {
            return 0;
        }
    }

    /**
     * 得到一个月所有的日期
     */
    public static String[] getMonthDays(String curday) {
        int counts = getMonthDaysCounts(curday);
        String[] pandy = new String[counts];
        String day = getCurMonthDayStr(curday);
        pandy[0] = getCurMonthDayStr(curday);
        for (int i = 1; i < counts; i++) {
            day = getNextDayStr(day);
            pandy[i] = day;
        }
        return pandy;
    }

    /**
     * 得到一个日期格式为YYYY年MM月DD日－MM月DD日
     *
     * @param start
     * @param end
     */
    public static String getymdmd(String start, String end) {
        String ymd = "";
        StringTokenizer fenxi = new StringTokenizer(start, "-");
        StringTokenizer fenxi2 = new StringTokenizer(end, "-");
        ymd += fenxi.nextToken() + "年  ";
        ymd += fenxi.nextToken() + "月 ";
        ymd += fenxi.nextToken() + "日 - ";
        fenxi2.nextToken();
        ymd += fenxi2.nextToken() + "月 ";
        ymd += fenxi2.nextToken() + "日";
        return ymd;
    }

    /**
     * 通过当前月，得到当前的日期yyyy-mm-01
     */
    public static String getFirstCurdata(String curmongth) {
        String pandy = "";
        String cur = getDateStr(new java.util.Date());
        if (curmongth.length() == 1) curmongth = "0" + curmongth;
        pandy = getYear(cur) + "-" + curmongth + "-01";
        return pandy;
    }

    /**
     * 通过当前月，得到当前的日期yyyy-mm-01
     */
    public static String getLastCurdata(String curmongth) {
        String pandy = "";
        String cur = getDateStr(new java.util.Date());
        if (curmongth.length() == 1) curmongth = "0" + curmongth;
        pandy = getYear(cur) + "-" + curmongth + "-" + getMonthDaysCounts(cur);
        return pandy;
    }

    /**
     * 得到当前日期得到当前的日期yyyy-mm-DD
     */
    public static String getToday() {
        return getDateStr(new java.util.Date());
    }

    /**
     * 判断给定日期是否为周六，日
     *
     * @param date
     */
    public static boolean isWeekend(String date) {
        return getCurWeekDayByStr(date) == 1 || getCurWeekDayByStr(date) == 7;
    }

    /**
     * 判断给定日期是否为五一十一假。
     *
     * @param date
     */
    public static boolean is51101(String date) {
        String strYue = date.substring(5, 7);
        String strRi = date.substring(8);
        int yue = Integer.parseInt(strYue);
        int ri = Integer.parseInt(strRi);
        return (yue == 5 || yue == 10) && ri <= 7;
    }

    /**
     * 初始化06--15年的春节假期
     */
    public static Map initChunJie() {
        String[] ChunJie = {"2006-01-29", "2007-02-18", "2008-02-07", "2009-01-26", "2010-02-14", "2011-02-03", "2012-01-23",
                "2013-02-10", "2014-01-31", "2015-02-20"};

        Map<String, String> dateMap = new HashMap<String, String>();

        for (int i = 0; i < ChunJie.length; i++) {
            String curDate = ChunJie[i];
            int j = 1;
            while (j <= 7) {
                dateMap.put(curDate, curDate);
                curDate = getNextDayStr(curDate);
                j++;
            }
        }
        return dateMap;
    }

    /**
     * 判断是否为春节、中秋、端午节。
     *
     * @param date
     */
    public static boolean isChunjie(String date) {
        return initChunJie().containsKey(date);
    }

    /**
     * 计算给定日期num个工作日后的日期。
     *
     * @param date
     * @param num
     */
    public static String getEndDay(String date, int num) {
        int i = 1;
        String flagMonth = date.substring(5, 7);
        date = getNextDayStr(date);
        if ((!flagMonth.equals("12")) && (!flagMonth.equals("01")) && (!flagMonth.equals("02")) && (!flagMonth.equals("04")) && (!flagMonth.equals("05")) && (!flagMonth.equals("09")) && (!flagMonth.equals("10")))
        {
            while (i <= num) {

                if (!isWeekend(date)) {

                    i++;
                }
                date = getNextDayStr(date);
            }
        } else
        if ((flagMonth.equals("04")) || flagMonth.equals("05") || (flagMonth.equals("09")) || flagMonth.equals("10")) {

            while (i <= num) {
                if ((!isWeekend(date)) && (!is51101(date))) {

                    i++;
                }
                date = getNextDayStr(date);
            }
        } else if ((flagMonth.equals("12")) || flagMonth.equals("01") || flagMonth.equals("02")) {
            while (i <= num) {

                if ((!isWeekend(date)) && (!isChunjie(date))) {
                    i++;
                }
                date = getNextDayStr(date);
            }
        }
        return getPreDayStr(date);
    }


    public static int getWorkDays(String endDate, String startDate) {
        if (endDate.compareToIgnoreCase(startDate) <= 0) {
            return 0;
        }
        int num = 0;
        startDate = getNextDayStr(startDate);
        while (startDate.compareToIgnoreCase(endDate) <= 0) {
            if ((!isWeekend(startDate)) && (!is51101(startDate)) && (!isChunjie(startDate))) {
                num++;
            }
            startDate = getNextDayStr(startDate);
        }
        return num;
    }

    //获得两个日期的间隔天数
    public static int getDays(Date sd, Date ed) {
        Long str = (ed.getTime() - sd.getTime()) / (3600 * 24 * 1000);
        return str.intValue();
    }



    //计算两个日期之间的月差
    public static int dispersionMonth2(String strDate1, String strDate2) {
        int iMonth = 0;
        int flag = 0;
        try {
            Calendar objCalendarDate1 = Calendar.getInstance();
            objCalendarDate1.setTime(DateFormat.getDateInstance().parse(strDate1));

            Calendar objCalendarDate2 = Calendar.getInstance();
            objCalendarDate2.setTime(DateFormat.getDateInstance().parse(strDate2));

            if (objCalendarDate2.equals(objCalendarDate1))
                return 0;
            if (objCalendarDate1.after(objCalendarDate2)) {
                Calendar temp = objCalendarDate1;
                objCalendarDate1 = objCalendarDate2;
                objCalendarDate2 = temp;
            }
            if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH))
                flag = 1;

            if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))
                iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR)) * 12
                        + objCalendarDate2.get(Calendar.MONTH) - flag) - objCalendarDate1.get(Calendar.MONTH);
            else
                iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH) - flag;

        }
        catch (Exception e) {
        }
        return iMonth;
    }

    //计算两个日期之间的月差
    public static int getmonthlen(String startDate, String endDate) {
        int len;
        int year = StringUtil.StringToInt(endDate.substring(0, 4)) - StringUtil.StringToInt(startDate.substring(0, 4));
        len = 12 * year + StringUtil.StringToInt(endDate.substring(5, 7)) - StringUtil.StringToInt(startDate.substring(5, 7)) + 1;
        return len;
    }

    //返回两个日期之间的年月，格式如下：YYYY-MM
    public static String[] getYYYYMM(String startDate, String endDate) {
        String a = (String) DateUtil.getMulmonthday(startDate, endDate);
        String[] temp = a.split(",");
        String[] str = new String[temp.length];
        for (int i = 0; i < temp.length; i++) {
            str[i] = temp[i].substring(0, 7);
        }
        return str;
    }
    
    public static String getYmdBystr(String str)
    {
    	String rs="";
    	java.util.Date curdate= new java.util.Date();
    	if(str==null||str.length()==0)
    	{
  
    	}
    	else if(str.equals("sys_curyear"))//当前年
    	{
    		rs=getDateStr(curdate).substring(0,4);
    	}
    	else if(str.equals("sys_curmoth"))//当前月
    	{
    		rs=getDateStr(curdate).substring(0,7).replace("-", "");
    	}
    	else if(str.equals("sys_curday"))//当前日
    	{
    		rs=getDateStr(curdate).replace("-", "");
    	}
    	else if(str.equals("sys_preyear"))//上年
    	{

    		rs=StringUtil.StringToInt(getYYYY(curdate))-1+"";
    	}
    	else if(str.equals("sys_premoth"))//上月
    	{
    		String temp=getPreMonthDayStr(getDateStr(curdate));
    		rs=temp.substring(0, 7).replace("-", "");
    	}
    	else if(str.equals("sys_preday"))//上日
    	{
    		String temp=getPreDayStr(getDateStr(curdate));
    		rs=temp.replace("-", "");
    	}else
    	{
    		rs=str;
    	}
    	return rs;
    }
    
    public static void main(String[] args)
    {
        //System.out.println(getYmdBystr("sys_curyear")+","+getYmdBystr("sys_curmoth")+","+getYmdBystr("sys_curday")+","+getYmdBystr("sys_preyear")+","+getYmdBystr("sys_premoth")+","+getYmdBystr("sys_preday"));
    }
}

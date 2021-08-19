package com.example.mylibrary.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

    private TimeUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取某一时间
     *
     * @param format 需要格式化的样式
     * @param time   时间
     * @return the current formatted time string
     */
    public static String getDateString(long time, SimpleDateFormat format) {
        return format.format(new Date(time));
    }

    /**
     * 消息在今天范围内，显示XX:XX，如 10:02
     * 消息在昨天范围内，显示昨天 XX:XX，如昨天10:02，昨天 18:10
     * 消息在昨天前且在今年内，显示 X月X日 XX:XX，如4月12日 10:02
     * 消息在今年以前，显示 X年X月X日 XX:XX，如2019年7月8日 10:02
     */
    public static String getFormatTime(Date date) {

        final String DateFormat_Today = "HH:mm";
        final String DateFormat_Yesterday = "昨天 HH:mm";
        final String DateFormat_Month = "M月d日 HH:mm";
        final String DateFormat_Year = "y年M月d日 HH:mm";

        SimpleDateFormat dateFormat;
        String time;

        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);

        Calendar targetCalendar = getCurrentDayCalendar();

        boolean dateFilter;

        dateFilter = dateCalendar.after(targetCalendar);//是否当天

        if (dateFilter) {
            dateFormat = new SimpleDateFormat(DateFormat_Today, Locale.CHINA);
            time = dateFormat.format(date);
            return time;
        }

        targetCalendar.add(Calendar.DATE, -1);
        dateFilter = dateCalendar.after(targetCalendar);//是否昨天

        if (dateFilter) {
            dateFormat = new SimpleDateFormat(DateFormat_Yesterday, Locale.CHINA);
            time = dateFormat.format(date);
            return time;
        }

        targetCalendar = getNowYearCalendar();
        dateFilter = dateCalendar.after(targetCalendar);//是否当年

        if (dateFilter) {
            dateFormat = new SimpleDateFormat(DateFormat_Month, Locale.CHINA);
            time = dateFormat.format(date);
            return time;
        }

        dateFormat = new SimpleDateFormat(DateFormat_Year, Locale.CHINA);
        time = dateFormat.format(date);

        return time;
    }

    private static Calendar getNowYearCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year, 0, 0, 0, 0,0);
        return calendar;
    }

    private static Calendar getCurrentDayCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar;
    }
}

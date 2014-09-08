package com.jk.makemoney.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chris.xue
 *         日期工具类
 */
public final class DateTimeUtils {
    private static final String TAG = "DateTimeUtils";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 抽取年月日
     *
     * @param date
     * @return
     */
    public static String extractDayOfYear(Date date) {
        return simpleDateFormat.format(date);
    }

    /**
     * 比较日期，需字符串格式为yyyy-MM-dd
     *
     * @param day1
     * @param day2
     * @return
     */
    public static int compareDay(String day1, String day2) {
        try {
            Date date1 = simpleDateFormat.parse(day1);
            Date date2 = simpleDateFormat.parse(day2);
            return date1.compareTo(date2);
        } catch (ParseException e) {
            return 0;
        }

    }

    public static Date convert(String s) {
        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            Log.e(TAG, "convert string to date failed", e);
        }
        return null;
    }
}

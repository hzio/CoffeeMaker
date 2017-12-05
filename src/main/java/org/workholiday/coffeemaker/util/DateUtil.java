package org.workholiday.coffeemaker.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Author:      Hunter Zhao
 * Mail:        zhaohevip@gmail.com
 * Date:        18:18 07/14/2017
 * Version:     1.0
 * Description: 时间工具类
 */
public class DateUtil {

    public static final String PATTERN_HH_MM               = "HH:mm";
    public static final String PATTERN_MM_DD               = "MM-dd";
    public static final String PATTERN_YYYYMMDD            = "yyyyMMdd";
    public static final String PATTERN_YYYY_MM_DD          = "yyyy-MM-dd";
    public static final String PATTERN_YYYY_MM             = "yyyyMM";
    public static final String PATTERN_YYYY_MM_DD_HH_MM    = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_HH_MM_YYYY_MM_DD    = "HH:mm yyyy/MM/dd";
    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_YYYYMMDDHHMM        = "yyyyMMddHHmm";
    public static final String PATTERN_YYMMDDHHMM          = "yyMMddHHmm";
    public static final String PATTERN_YY_MM_DD_HH_MM      = "yy/MM/dd HH:mm";

    private DateUtil() {
        // to avoid creating instance
    }

    /**
     * 格式化时间
     *
     * @param date    时间对象
     * @param pattern 时间格式
     * @return 格式化后的时间字符串
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 转换时间字符串为Date对象
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateStr) {
        return parse(dateStr, PATTERN_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 转换时间字符串为Date对象
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            // ignore
        }
        return date;
    }

}

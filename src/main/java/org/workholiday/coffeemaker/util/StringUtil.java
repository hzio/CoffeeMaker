package org.workholiday.coffeemaker.util;


import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Function: 字符串工具类
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     15:43 06/15/2017
 * Version:  1.0
 */
public class StringUtil {

    private static final int DEFAULT_EXPECT_LOG_LENGTH = 60;


    private StringUtil() {
        // to avoid creating instance
    }

    /**
     * 首字母转换成大写
     * @param origin
     * @return
     */
    public static String capitalize(String origin) {
        return convertFirstLetterCase(origin, true);
    }

    /**
     * 首字母转换成小写
     * @param origin
     * @return
     */
    public static String firstLetter2LowerCase(String origin) {
        return convertFirstLetterCase(origin, false);
    }

    /**
     * 转换大小写
     * @param origin
     * @param toUpper
     * @return
     */
    private static String convertFirstLetterCase(String origin, boolean toUpper) {

        if (ObjectUtil.isBlank(origin)) {
            return origin;
        }

        String first = origin.substring(0, 1);
        String rest = origin.substring(1);

        String firstLetter = toUpper ? first.toUpperCase() : first.toLowerCase();
        return firstLetter + rest;
    }


    /**
     * 以.....格式化固定长度日志
     * e.g.
     * Parse Foo.java successfully....................
     * Parse FooBar.java successfully.................
     * @param text
     * @return
     */
    public static String formatLogTextWithDots(String text) {

        Objects.requireNonNull(text);

        StringBuilder tmp = new StringBuilder(text);
        tmp.append(" ");
        for (int i = 0; i < DEFAULT_EXPECT_LOG_LENGTH - text.length(); i++) {
            tmp.append(".");
        }

        return tmp.toString();
    }

    /**
     * 格式化日志时间
     * @param duration  单位：毫秒
     * @return
     */
    public static String formatLogTime(long duration) {

        Double costTime = Double.valueOf(duration) / 1000;
        DecimalFormat format = new DecimalFormat("0.000");
        return format.format(costTime);
    }


}

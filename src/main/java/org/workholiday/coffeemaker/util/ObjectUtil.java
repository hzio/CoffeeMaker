package org.workholiday.coffeemaker.util;

import java.util.Collection;
import java.util.Map;

/**
 * Function: 对象工具类
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     15:15 02/23/2017
 * Version:  1.0
 */
public class ObjectUtil {

    private ObjectUtil() {
        // to avoid creating instance
    }

    /**
     * judge a given string if blank
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * judge a given string if not blank
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * judge a given collection if blank
     *
     * @param col
     * @return
     */
    public static boolean isBlank(Collection col) {
        return col == null || col.isEmpty();
    }

    /**
     * judge a given collection if not blank
     *
     * @param col
     * @return
     */
    public static boolean isNotBlank(Collection col) {
        return !isBlank(col);
    }

    /**
     * judge a given map if blank
     *
     * @param map
     * @return
     */
    public static boolean isBlank(Map map) {
        return map == null || map.isEmpty();
    }

    /**
     * judge a given map if not blank
     *
     * @param map
     * @return
     */
    public static boolean isNotBlank(Map map) {
        return !isBlank(map);
    }

    /**
     * judge a given array if blank
     *
     * @param objs
     * @return
     */
    public static boolean isBlank(Object[] objs) {
        return objs == null || objs.length == 0;
    }

    /**
     * judge a given array if not blank
     *
     * @param objs
     * @return
     */
    public static boolean isNotBlank(Object[] objs) {
        return !isBlank(objs);
    }

    /**
     * return empty string if the given string is empty or null
     * otherwise return the original string
     *
     * @param str
     * @return
     */
    public static String emptyIfBlank(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        return str;
    }

}

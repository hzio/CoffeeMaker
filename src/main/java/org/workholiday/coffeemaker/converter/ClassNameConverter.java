package org.workholiday.coffeemaker.converter;

import org.workholiday.coffeemaker.util.ObjectUtil;
import org.workholiday.coffeemaker.util.StringUtil;

/**
 * Function: 类名转换器
 *
 * e.g. t_order  ->  Order
 *
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     15:37 06/15/2017
 * Version:  1.0
 */
public class ClassNameConverter implements NameCaseConverter<String, String> {

    private String tablePrefix;

    public static final String DEFAULT_TABLE_PREFIX = "t";

    public ClassNameConverter() {
    }

    public ClassNameConverter(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    @Override
    public String convert(String origin) {

        if (ObjectUtil.isBlank(origin)) {
            return origin;
        }

        StringBuilder tmp = new StringBuilder();
        String[] arr = origin.toLowerCase().split("_");
        for (int i = 0; i < arr.length; i++) {
            String ch = arr[i];
            if (i == 0) {
                if (ch.equals(tablePrefix) || DEFAULT_TABLE_PREFIX.equals(ch)) {
                    continue;
                } else {
                    tmp.append(StringUtil.capitalize(arr[i]));
                }
            }
            else {
                tmp.append(StringUtil.capitalize(arr[i]));
            }
        }
        return tmp.toString();
    }

}

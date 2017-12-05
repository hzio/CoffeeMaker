package org.workholiday.coffeemaker.converter;

import org.workholiday.coffeemaker.util.ObjectUtil;
import org.workholiday.coffeemaker.util.StringUtil;

/**
 * Function: 字段转换器
 *
 * e.g. user_name -> userName
 *
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     15:59 06/15/2017
 * Version:  1.0
 */
public class FiledNameConverter implements NameCaseConverter<String, String> {

    @Override
    public String convert(String columnName) {

        if (ObjectUtil.isBlank(columnName)) {
            return columnName;
        }

        if (!columnName.contains("_")) {
            return columnName;
        }
        else {
            StringBuilder tmp = new StringBuilder();
            String[] arr = columnName.toLowerCase().split("_");
            for (int i = 0; i < arr.length; i++) {
                if (i == 0) {
                    tmp.append(arr[i]);
                }
                else {
                    tmp.append(StringUtil.capitalize(arr[i]));
                }
            }
            return tmp.toString();
        }
    }
}

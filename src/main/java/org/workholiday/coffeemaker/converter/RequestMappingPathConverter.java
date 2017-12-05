package org.workholiday.coffeemaker.converter;

import org.workholiday.coffeemaker.util.ObjectUtil;

/**
 * Author:      Hunter Zhao
 * Mail:        zhaohevip@gmail.com
 * Date:        00:24 07/20/2017
 * Version:     1.0
 * Description: RequestMapping路径转换器
 */
public class RequestMappingPathConverter implements NameCaseConverter<String, String> {

    @Override
    public String convert(String className) {

        if (ObjectUtil.isBlank(className)) {
            return className;
        }

        char[] arr = className.toCharArray();
        StringBuilder tmp = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {

            char c = arr[i];
            if (Character.isUpperCase(c)) {
                if (i != 0) {
                    tmp.append("/");
                }
                tmp.append(c);
            }
            else {
                tmp.append(c);
            }
        }
        return tmp.toString().toLowerCase();

    }

}

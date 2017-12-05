package org.workholiday.coffeemaker.converter;

/**
 * Function: 转换器
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     14:26 06/14/2017
 * Version:  1.0
 */
public interface Converter<T, E> {

    /**
     * 转换数据
     * @param e 源数据
     * @return  目标数据
     */
    T convert(E e);

}

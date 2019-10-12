package com.baomidou.mybatisplus.samples.crud.framework.mp.parser;

import java.util.Arrays;
import java.util.List;

/**
 * @author joking
 */
public class ShadingTableNameHolder {

    private ShadingTableNameHolder() {}

    static ThreadLocal<List<Object>> TABLE_NAMES = new ThreadLocal<>();

    public static void setTableNameParams(Object ... params) {
        TABLE_NAMES.remove();
        TABLE_NAMES.set(Arrays.asList(params));
    }

    public static List<Object> getTableNameParams() {
        return TABLE_NAMES.get();
    }
}

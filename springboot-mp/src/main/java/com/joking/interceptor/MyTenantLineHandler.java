package com.joking.interceptor;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

public class MyTenantLineHandler implements TenantLineHandler {


    @Override
    public Expression getTenantId() {

        // 从 ThreadLocal 中获取到 organization 的值
        return new LongValue(2);
    }

    @Override
    public String getTenantIdColumn() {
        return "organization";
    }

    /**
     * 忽略处理的表， 有写表如果没有 organization 字段可以在这儿忽略掉
     * @param tableName 表名
     */

    @Override
    public boolean ignoreTable(String tableName) {
        return TenantLineHandler.super.ignoreTable(tableName);
    }
}

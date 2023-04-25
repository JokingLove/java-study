package com.joking.strategy;

import com.baomidou.dynamic.datasource.strategy.DynamicDataSourceStrategy;

import java.util.List;

/**
 * @author joking
 */
public class TenantDatabaseSelectorStrategy implements DynamicDataSourceStrategy {
    @Override
    public String determineDSKey(List<String> list) {
        System.out.println(list);
        return "tenant_ds3";
    }
}

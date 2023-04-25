package com.joking.provider;

import com.baomidou.dynamic.datasource.provider.AbstractDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.druid.DruidConfig;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.joking.entity.TenantConfig;
import com.joking.service.TenantConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author joking
 */
@Slf4j
@Component
public class DatabaseDataSourceProvider extends AbstractDataSourceProvider {

    private final DynamicDataSourceProperties dynamicDataSourceProperties;
    private final TenantConfigService tenantConfigService;

    public DatabaseDataSourceProvider(DynamicDataSourceProperties dynamicDataSourceProperties, TenantConfigService tenantConfigService) {
        this.dynamicDataSourceProperties = dynamicDataSourceProperties;
        this.tenantConfigService = tenantConfigService;
    }

    @Override
    public Map<String, DataSource> loadDataSources() {
        Map<String, DataSourceProperty> dataBaseDataSourcePropertyMap = this.getDataSourcePropertyMapFromDatabase();
        return createDataSourceMap(dataBaseDataSourcePropertyMap);
    }

    private Map<String, DataSourceProperty> getDataSourcePropertyMapFromDatabase() {
        List<TenantConfig> allTenantConfig = tenantConfigService.getAllTenantConfig();
        Map<String, DataSourceProperty> dataBaseDataSourcePropertyMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(allTenantConfig)) {
            DruidConfig druid = dynamicDataSourceProperties.getDruid();
            log.info("dynamic datasource global config : {}", dynamicDataSourceProperties);
            for (TenantConfig tenantConfig : allTenantConfig) {
                DataSourceProperty tenantDataSourceProperty = new DataSourceProperty();
                tenantDataSourceProperty.setDriverClassName(tenantConfig.getDriverClassName())
                        .setUrl(tenantConfig.getUrl())
                        .setUsername(tenantConfig.getUsername())
                        .setPassword(tenantConfig.getPassword())
                        .setDruid(druid)
                        .setLazy(true);
                dataBaseDataSourcePropertyMap.put(tenantConfig.getTenantCode(), tenantDataSourceProperty);
            }
        }
        return dataBaseDataSourcePropertyMap;
    }
}

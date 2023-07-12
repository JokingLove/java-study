package com.joking.config;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.joking.interceptor.MyTenantLineHandler;
import com.joking.interceptor.TenantInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author joking
 */
@Configuration
public class DynamicAutoConfiguration {


    @Autowired
    private final DynamicDataSourceProperties dynamicDataSourceProperties;

    public DynamicAutoConfiguration(DynamicDataSourceProperties dynamicDataSourceProperties) {
        this.dynamicDataSourceProperties = dynamicDataSourceProperties;
    }


    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new MyTenantLineHandler()));
        return interceptor;
    }


//    @Bean
//    public DynamicDataSourceProvider dynamicDataSourceProvider() {
//        Map<String, DataSourceProperty> datasource = dynamicDataSourceProperties.getDatasource();
//
//
//        return new DatabaseDataSourceProvider(datasource, tenantConfigService);
//    }

//    @Bean
//    public DatabaseDataSourceCreator databaseDataSourceCreator() {
//        return new DatabaseDataSourceCreator();
//    }
}

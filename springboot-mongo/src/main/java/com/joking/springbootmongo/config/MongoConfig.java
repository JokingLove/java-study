package com.joking.springbootmongo.config;

import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientSettingsFactoryBean;

import java.util.concurrent.TimeUnit;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClientSettingsFactoryBean mongoClientSettingsFactoryBean() {
        MongoClientSettingsFactoryBean factoryBean = new MongoClientSettingsFactoryBean();
        factoryBean.setPoolMaxConnectionIdleTimeMS(5000);
        factoryBean.setPoolMaxSize(100);
        factoryBean.setPoolMinSize(10);
        return factoryBean;
    }

    @Bean
    public MongoClientSettingsBuilderCustomizer mongoClientSettingsBuilderCustomizer() {
        MongoClientSettingsBuilderCustomizer builder = clientSettingsBuilder -> clientSettingsBuilder.applyToConnectionPoolSettings(settings -> {
            settings.maxConnectionIdleTime(1000, TimeUnit.SECONDS);
            settings.maxSize(100);
            settings.minSize(3);
        });
        return builder;
    }
}

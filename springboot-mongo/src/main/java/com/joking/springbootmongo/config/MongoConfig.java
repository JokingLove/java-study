package com.joking.springbootmongo.config;

import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientSettingsFactoryBean;

import java.util.concurrent.TimeUnit;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

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
        MongoClientSettingsBuilderCustomizer builder = clientSettingsBuilder -> {
            clientSettingsBuilder.applyToConnectionPoolSettings(settings -> {
                settings.maxConnectionIdleTime(1000, TimeUnit.SECONDS);
                settings.maxSize(100);
                settings.minSize(3);
            });
            clientSettingsBuilder.codecRegistry(this.codecRegistry());
        };
        return builder;
    }

    public CodecRegistry codecRegistry() {
        return fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    }
}

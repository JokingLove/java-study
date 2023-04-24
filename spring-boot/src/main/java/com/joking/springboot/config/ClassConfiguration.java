package com.joking.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static com.joking.springboot.config.ClassConfiguration.PREFIX;

/**
 * @author joking
 */
@Data
@Configuration
@ConfigurationProperties(prefix = PREFIX)
public class ClassConfiguration {

    final static String PREFIX = "test.clazz";

    private String name;

    private Class<?> testClass;
}

package com.joking.springboot;

import com.joking.springboot.config.ClassConfiguration;
import com.joking.springboot.config.CourseProperties;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.lang.reflect.Field;


/**
 * @author joking
 */
@Import(CourseProperties.class)
@SpringBootApplication
@EnableConfigurationProperties
public class Application implements ApplicationRunner{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Resource
    private ClassConfiguration configuration;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(configuration);
        Class<?> testClass = configuration.getTestClass();
        Field[] declaredFields = testClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
    }
}


package com.joking.springboot;

import com.joking.springboot.config.CourseProperties;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;


/**
 * @author joking
 */
@Import(CourseProperties.class)
@SpringBootApplication
@EnableConfigurationProperties
public class Application implements ApplicationRunner {

    @Resource
    private CourseProperties courseConfig;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(courseConfig);
    }
}


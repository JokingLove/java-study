package com.joking.springboot;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.joking.springboot.config.ClassConfiguration;
import com.joking.springboot.config.CourseProperties;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.Arrays;


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


    /** fastJson 配置信息 **/
    @Bean
    public HttpMessageConverters fastJsonConfig(){

        //新建fast-json转换器
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        //fast-json 配置信息
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(config);


        //设置响应的 Content-Type
        converter.setSupportedMediaTypes(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8}));
        return new HttpMessageConverters(converter);
    }
}


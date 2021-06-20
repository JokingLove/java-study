package com.joking.springboot.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author <a href="mailto:jokinglove@foxmail.com">JOKING</a>
 * @since 2021/5/4
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "course")
public class CourseProperties {

    private String name;

    private Map<String, Student> students;

}

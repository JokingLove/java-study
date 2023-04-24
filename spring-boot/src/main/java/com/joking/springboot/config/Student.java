package com.joking.springboot.config;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 学生
 *
 * @author <a href="mailto:jokinglove@foxmail.com">JOKING</a>
 * @since 2021/5/4
 */
@Data
@ToString
@Accessors(chain = true)
public class Student {

    private String name;

    private String sex;

    private Integer age;

}

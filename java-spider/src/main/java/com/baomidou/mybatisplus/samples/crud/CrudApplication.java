package com.baomidou.mybatisplus.samples.crud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CrudApplication.class)
                .run(args);
    }
}


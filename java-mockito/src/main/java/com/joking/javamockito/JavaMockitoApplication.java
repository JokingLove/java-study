package com.joking.javamockito;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class JavaMockitoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(JavaMockitoApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}

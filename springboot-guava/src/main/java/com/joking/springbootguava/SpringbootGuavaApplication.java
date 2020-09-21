package com.joking.springbootguava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class SpringbootGuavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootGuavaApplication.class, args);
    }

    @Service
    public class TestService implements ApplicationRunner {
        @Value("${test:haha}")
        private String test;

        public void haha() {
            System.out.println(this.test);
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {
            this.haha();
        }
    }

}

package com.joking.test;

import com.joking.test.bean.ClassA;
import com.joking.test.bean.ClassB;
import com.joking.test.event.ApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application {

    @Autowired
    private ClassA classA;

    @Autowired
    private ClassB classB;

    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }


    @Component
    class StartRun implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) throws Exception {
            System.out.println("classA:   ====> " + classA);
            System.out.println("classB:   ====> " + classB);
        }
    }
}

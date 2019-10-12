package com.baomidou.mybatisplus.samples.crud;

import com.baomidou.mybatisplus.samples.crud.task.AutoComputeTask;
import com.baomidou.mybatisplus.samples.crud.task.SohuStockPriceTask;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author joking
 */
@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CrudApplication.class)
                .run(args);
    }

    @Resource
    private AutoComputeTask autoComputeTask;
    @Resource
    private SohuStockPriceTask sohuStockPriceTask;
    @Component
    class StartRunner implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) throws Exception {
//            sohuStockPriceTask.start();
//            autoComputeTask.start();
        }
    }
}


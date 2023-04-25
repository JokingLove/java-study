package com.joking;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.joking.entity.AuditFlow;
import com.joking.service.AuditFlowService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;


/**
 * Hello world!
 *
 * @author joking
 */

@MapperScan("com.joking.mapper")
@SpringBootApplication
public class Application implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Resource
    private AuditFlowService auditFlowService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        DynamicDataSourceContextHolder.push("tenantDs4");

        AuditFlow auditFlow = auditFlowService.getById(1234);
        System.out.println(auditFlow);
    }
}

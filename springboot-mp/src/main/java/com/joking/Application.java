package com.joking;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.joking.entity.AuditFlow;
import com.joking.service.AuditFlowService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;


/**
 * Hello world!
 *
 * @author joking
 */

@Slf4j
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
//        DynamicDataSourceContextHolder.push("tenantDs4");

//        AuditFlow auditFlow = auditFlowService.findById(1234);
//        System.out.println(auditFlow);

        log.info("spring boot version : {}", SpringBootVersion.getVersion());


        String implementationVersion = Application.class.getPackage().getImplementationVersion();
        System.out.println(implementationVersion);

        String druidVersion = Package.getPackage("com.alibaba.druid")
                .getImplementationVersion();
        System.out.println(druidVersion);
    }
}

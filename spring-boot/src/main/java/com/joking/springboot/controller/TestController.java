package com.joking.springboot.controller;

import com.joking.springboot.entity.ApiRes;
import com.joking.springboot.entity.TestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get")
    public ApiRes<Serializable> getEntity() {
        TestEntity testEntity = new TestEntity();
        testEntity.setAge(18)
                .setName("张三")
                .setBirthday(new Date())
                .setAmount(100003334L);
        return ApiRes.ok(testEntity);
    }
}

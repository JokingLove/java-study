package com.joking.javamockito;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(String name) {
        return "hello world! " + name;
    }

    @GetMapping("/entity")
    public Entity returnParm(Entity entity) {
        return  entity;
    }
}

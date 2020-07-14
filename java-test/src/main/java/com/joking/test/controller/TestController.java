package com.joking.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("hello")
    public String hello(String name) {
        return "hello " + name;
    }
}

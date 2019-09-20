package com.joking.spi.jdk;

public class Dog implements Animal {
    @Override
    public void say() {
        System.out.println("阿狗🐶！");
    }
}

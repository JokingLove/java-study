package com.joking.spi.jdk;

public class Cat implements Animal {
    @Override
    public void say() {
        System.out.println("阿猫🐈！");
    }
}

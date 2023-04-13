package com.joking.test.datafaker;

import net.datafaker.Faker;

import java.util.Locale;

/**
 * datafaker 测试类
 */
public class DataFakerMain {

    public static void main(String[] args) {
        Faker faker = new Faker(Locale.CHINA);
        for (int i = 0; i < 100; i++) {
            System.out.println("name-name : " + faker.name().fullName());
            System.out.println("city : " + faker.address().city());
            System.out.println("avatar : " + faker.avatar().image());
            System.out.println("animal : " + faker.animal().name());
        }
    }
}

package com.joking.test.function;


import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * 函数是接口测试类
 * @author joking
 */
public class FunctionalTest {
    public static void main(String[] args) {
        Consumer<String> consumer = System.out::println;
        consumer.accept("aa");

        BiConsumer<String, String> biConsumer = (s1, s2) -> System.out.println(s1 + s2);
        biConsumer.accept("12", "3");

    }
}


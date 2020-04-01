package com.fastrun.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author joking
 */
public class TestStream {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> collect = integers.stream()
                .filter(i -> i % 2 == 1)
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());
        boolean b = collect.stream().allMatch(i -> i == 0);
        System.out.println(collect);
        System.out.println(b);
    }
}

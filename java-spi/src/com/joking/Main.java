package com.joking;

import com.joking.spi.jdk.Animal;

import java.util.ServiceLoader;

public class Main {

    public static void main(String[] args) {
        ServiceLoader<Animal> animals = ServiceLoader.load(Animal.class);
        animals.forEach(System.out::println);
        animals.forEach(Animal::say);
    }
}

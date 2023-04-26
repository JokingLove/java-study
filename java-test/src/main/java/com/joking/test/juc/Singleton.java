package com.joking.test.juc;

/**
 * @author joking
 */
public class Singleton {
    private Singleton() {
        System.out.println("hahahaha");
    }

    private static Singleton singleton = null;

    public static synchronized Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
package com.joking.test.object;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

public class ObjectMemory {

    private static class Test {

    }

    public static void main(String[] args) throws Exception {
        Test t = new Test();
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
        while(true) {
            System.out.println("1111");
            TimeUnit.SECONDS.sleep(1000);
        }
    }
}

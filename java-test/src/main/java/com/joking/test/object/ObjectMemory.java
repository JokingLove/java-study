package com.joking.test.object;

import org.openjdk.jol.info.ClassLayout;

public class ObjectMemory {

    private static class Test {

    }

    public static void main(String[] args) {
        Test t = new Test();
        System.out.println(ClassLayout.parseInstance(t).toPrintable());

    }
}

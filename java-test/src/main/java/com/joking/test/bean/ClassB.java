package com.joking.test.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassB {

//    private final ClassA classA;
//
//    public ClassB(ClassA classA) {
//        this.classA = classA;
//    }

    @Autowired
    private ClassA classA;

//    @Override
//    public String toString() {
//        return "ClassB{" +
//                "classA=" + classA +
//                '}' + super.toString();
//    }
}

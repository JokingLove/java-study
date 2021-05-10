package com.joking.test.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassA {

//    private final ClassB classB;
//
//    public ClassA(ClassB classB) {
//        this.classB = classB;
//    }

    @Autowired
    private ClassB classB;

//    @Override
//    public String toString() {
//        return "ClassA{" +
//                "classB=" + classB +
//                '}' + super.toString();
//    }
}

package com.joking.springboot.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author <a href="mailto:jokinglove@foxmail.com">JOKING</a>
 * @since 2021/6/20
 */
@Component
public class A {

    @Autowired
    private B b;
}

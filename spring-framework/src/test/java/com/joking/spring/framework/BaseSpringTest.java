package com.joking.spring.framework;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class BaseSpringTest {

    protected AnnotationConfigApplicationContext context;


    @Before
    public void startContext() {
        context = new AnnotationConfigApplicationContext(getConfigClasses());
        context.refresh();
        context.start();
    }

    private Class<?>[] getConfigClasses() {
        return configClasses();
    }

    protected abstract Class<?>[] configClasses();

    @After
    public void closeContext() {
        context.close();
    }
}

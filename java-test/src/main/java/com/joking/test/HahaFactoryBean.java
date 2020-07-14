package com.joking.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import java.lang.reflect.Constructor;

public class HahaFactoryBean implements FactoryBean<Object>, InitializingBean,
        ApplicationContextAware {

    private Class<?> type;

    private String name;

    private ApplicationContext applicationContext;


    @Override
    public Object getObject() throws Exception {
        Assert.notNull(name, "bean name must be not null");
        Constructor<?> declaredConstructor = type.getDeclaredConstructor();
        return declaredConstructor.newInstance();
    }

    @Override
    public Class<?> getObjectType() {
        return this.type;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

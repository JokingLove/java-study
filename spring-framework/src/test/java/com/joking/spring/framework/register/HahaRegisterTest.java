package com.joking.spring.framework.register;

import com.joking.spring.framework.BaseSpringTest;
import com.joking.spring.framework.HahaAnnotated;
import org.junit.Test;

public class HahaRegisterTest extends BaseSpringTest {

    @Test
    public void registerBeanDefinitions() {
        Object hahaAnnotated = context.getBean("hahaAnnotated");
        System.out.println(hahaAnnotated);
    }

    @Override
    protected Class<?>[] configClasses() {
        return new Class[]{HahaAnnotated.class};
    }
}
package com.joking.spring.framework.register;

import com.joking.spring.framework.annotations.Haha;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author guoyongqiang
 */
public class HahaRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(Haha.class.getName()));
        if (annotationAttributes != null) {
            registBeanDefinition(importingClassMetadata, registry, );
        }
    }
}

package com.joking.test;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.MultiValueMap;

import java.util.Map;
import java.util.Set;

public class HahaRegistrar implements ImportBeanDefinitionRegistrar,
        ResourceLoaderAware, EnvironmentAware {

    private ResourceLoader resourceLoader;
    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        ClassPathScanningCandidateComponentProvider scanning = new ClassPathScanningCandidateComponentProvider(false, this.environment) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return super.isCandidateComponent(beanDefinition);
            }
        };
        scanning.setResourceLoader(resourceLoader);
        scanning.setEnvironment(environment);

        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(Haha.class);
        scanning.addIncludeFilter(annotationTypeFilter);

        scanning.addIncludeFilter(annotationTypeFilter);

        String packageName = ClassUtils.getPackageName(metadata.getClass());
        Set<BeanDefinition> candidateComponents = scanning.findCandidateComponents(packageName);
        for (BeanDefinition candidateComponent : candidateComponents) {
            if(candidateComponent instanceof AnnotatedBeanDefinition) {
                AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition) candidateComponent;
                AnnotationMetadata annotationMetadata = annotatedBeanDefinition.getMetadata();
                Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(Haha.class.getName());
                String name = (String) annotationAttributes.get("name");
                BeanDefinitionHolder holder = new BeanDefinitionHolder(candidateComponent,
                        annotationMetadata.getClassName(),
                        new String[] {name});
                BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
            }
        }


    }
}

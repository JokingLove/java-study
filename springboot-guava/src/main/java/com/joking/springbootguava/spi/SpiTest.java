package com.joking.springbootguava.spi;

import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.util.ClassUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.*;

public class SpiTest {

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> urls = classLoader.getResources("META-INF/spring.factories");

        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        while(urls.hasMoreElements()) {
            URL url = urls.nextElement();
            InputStream in = url.openStream();
            Properties prop = new Properties();
            prop.load(in);
            prop.forEach((k, v) -> {
                String value = (String)v;
                if(StringUtils.hasText(value)) {
                    String[] valueStrs = StringUtils.delimitedListToStringArray(value, ",");
                    if(value.length() > 0) {
                        Arrays.stream(valueStrs).forEach(i -> {
                            try {
                                Class<?> aClass = ClassUtils.forName(i, classLoader);
                                Constructor<?> ctor = aClass.getDeclaredConstructor(new Class<?>[] {});
                                Object o = ctor.newInstance();
                                map.add((String) k, o);
                            } catch (Exception e) {
                                System.out.printf("类加载错误：[%s] %s %n", i, e.getMessage());
                            }
                        });
                    }
                }
            });
        }

        System.out.println(list.size());

        System.out.println("=====");

        System.out.println(set.size());


        System.out.println(map);
    }
}

package com.joking.test;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.regex.Pattern;

public class Test {


    @PostConstruct
    public void init() {
        System.out.println("init 方法调用！");
    }

    static final  Pattern NAME_SEPARATOR = Pattern.compile("\\s*[,]+\\s*");

    private static final String  DIR = "META-INF/test/";

    public static void mai2n(String[] args) throws IOException {
        String name = "hah,,aha";
        String[] split = NAME_SEPARATOR.split(name);
        Arrays.asList(split).forEach(System.out::println);
        ClassLoader classLoader = Test.class.getClassLoader();
        if(classLoader != null) {
            Enumeration<URL> urls = classLoader.getResources(DIR + Animal.class.getName());
            while(urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if(url != null) {
                    InputStream inputStream = url.openStream();

                }
            }
        }

    }

    public static void main(String[] args) {
        new Test();
    }
}

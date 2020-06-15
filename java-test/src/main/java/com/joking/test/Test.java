package com.joking.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.regex.Pattern;

public class Test {

    static final  Pattern NAME_SEPARATOR = Pattern.compile("\\s*[,]+\\s*");

    private static final String  DIR = "META-INF/test/";

    public static void main(String[] args) throws IOException {
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
}

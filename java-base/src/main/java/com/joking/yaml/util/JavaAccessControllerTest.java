package com.joking.yaml.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;

/**
 * @author joking
 */
public class JavaAccessControllerTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(2);
        map.put("hello", "world!");
        map.put("111", "222");

        Class<HashMap> hashMapClass = HashMap.class;

        AccessController.doPrivileged((PrivilegedAction<Map<String, String>>) () -> {

            try {
                Method getMethod = hashMapClass.getDeclaredMethod("get", Object.class);
                getMethod.setAccessible(true);
                Object hello = getMethod.invoke(map, "hello");
                System.out.println(hello);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
    }
}

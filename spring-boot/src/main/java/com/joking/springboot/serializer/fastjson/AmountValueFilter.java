package com.joking.springboot.serializer.fastjson;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.joking.springboot.serializer.AmountField;
import com.joking.springboot.utils.AmountUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class AmountValueFilter implements ValueFilter {


    @Override
    public Object process(Object object, String name, Object value) {
        Object resultValue = value;
        try {
            Field field = object.getClass().getDeclaredField(name);
            AmountField annotation = field.getAnnotation(AmountField.class);
            if (annotation != null && value != null) {
                resultValue = AmountUtil.convertCent2Dollar((long)value);
            }
        } catch (NoSuchFieldException var7) {
            log.error("fastJson 序列化出错 {}#{}:{}", object.getClass(), name, value);
        }
        return resultValue;
    }
}

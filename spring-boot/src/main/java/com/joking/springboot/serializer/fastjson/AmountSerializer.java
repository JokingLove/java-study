package com.joking.springboot.serializer.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.joking.springboot.utils.AmountUtil;

import java.io.IOException;
import java.lang.reflect.Type;

public class AmountSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer jsonSerializer, Object value, Object fieldName, Type fieldType, int i) throws IOException {
        System.out.println("haah ====== filedName: " + fieldName + ", value : " + value);
        String resultValue = "";
        if(value instanceof Long) {
            resultValue = AmountUtil.convertCent2Dollar((long)value);
        }
        System.out.println("写出的值为：" + resultValue);
        jsonSerializer.write(resultValue);
    }
}

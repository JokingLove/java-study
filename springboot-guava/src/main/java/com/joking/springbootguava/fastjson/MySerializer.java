package com.joking.springbootguava.fastjson;


import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class MySerializer implements ObjectSerializer {

    public static final MySerializer instance = new MySerializer();


    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
        if(o == null) {
            o = new Object();
        }
        jsonSerializer.out.write(o.toString());
    }
}
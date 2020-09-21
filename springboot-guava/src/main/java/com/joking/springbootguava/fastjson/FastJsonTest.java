package com.joking.springbootguava.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.*;

import java.io.IOException;
import java.lang.reflect.Type;

public class FastJsonTest {

    public static void main(String[] args) {
        User user = new User();
        user.setId(1);

//        SerializeConfig.getGlobalInstance().put(User.class, new MySerializer());
        SerializeConfig config = new SerializeConfig();
        config.put(Long.class, MySerializer.instance);
        config.put(long.class, MySerializer.instance);
        config.put(String.class, MySerializer.instance);
        String s = JSON.toJSONString(user, config);
        System.out.println(s);
    }

}

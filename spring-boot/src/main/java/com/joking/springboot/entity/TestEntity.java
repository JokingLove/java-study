package com.joking.springboot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.joking.springboot.serializer.fastjson.AmountSerializer;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)

@ToString
@EqualsAndHashCode(callSuper = false)
public class TestEntity implements Serializable {

    private int age;

    private String name;

    private Date birthday;

    @JSONField(serializeUsing = AmountSerializer.class)
    private Long amount;
}

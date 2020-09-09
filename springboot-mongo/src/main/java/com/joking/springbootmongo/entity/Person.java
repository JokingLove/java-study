package com.joking.springbootmongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("person")
public class Person{
    private String name;
    private int age;
    private List<Address> address;


    @Data
    @ToString
    @AllArgsConstructor
    public static class Address {
        private String province;
        private String city;
        private String street;
        private int no;

    }
}

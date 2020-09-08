package com.joking.springbootmongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
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

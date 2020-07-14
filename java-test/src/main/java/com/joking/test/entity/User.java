package com.joking.test.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.*;
import java.util.Date;

@Data
@Document("user")
@Accessors(chain = true)
public class User {

    @Id
    private String id;

    private String name;

    private String password;

    private Date birthday;

    private Address address;


//    private static byte[] readInputStream(InputStream inputStream) throws IOException {
//        byte[] buffer = new byte[1024];
//        int len = 0;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        while((len = inputStream.read(buffer)) != -1) {
//            bos.write(buffer, 0, len);
//        }
//        return bos.toByteArray();
//    }
//
//    public static String file = "/Users/joking/Downloads/360Browser_for_mac_1.0.1505.0.dmg";
//    public static void main(String[] args) throws Exception {
//        read1();
//        read2();
//    }
//
//    public static void read1() throws Exception {
//        long l = System.currentTimeMillis();
//        InputStream inputStream = new FileInputStream(file);
//        byte[] data = new byte[inputStream.available()];
//        inputStream.read(data, 0 ,inputStream.available());
//
////        byte[] data = readInputStream(inputStream);
//
//        System.out.println(data.length);
//        System.out.println(System.currentTimeMillis() -l);
//    }
//
//    public static void read2() throws Exception {
//        long l = System.currentTimeMillis();
//        InputStream inputStream = new FileInputStream(file);
////        byte[] data = new byte[inputStream.available()];
////        inputStream.read(data, 0 ,inputStream.available());
//
//        byte[] data = readInputStream(inputStream);
//
//        System.out.println(data.length);
//        System.out.println(System.currentTimeMillis() -l);
//    }

    @Data
    @Accessors(chain = true)
    public static class Address {
        private String id;
        private String province;
        private String city;
    }

}



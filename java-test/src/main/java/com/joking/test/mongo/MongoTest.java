package com.joking.test.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ReadPreference;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoTest {
    static String uri1 = "mongodb://MEDBUser:MEDBUser@172.172.172.172:20000,172.172.172.173:20000,172.172.172.174:20000/MedicalExaminationDB3";
    static String uri2 = "mongodb://MEDBUser:MEDBUser@172.172.172.172:20000,172.172.172.173:20000,172.172.172.174:20000/MedicalExaminationDB3?readPreference=secondary";

    public static void main(String[] args) {
        MongoCollection<Document> test1 = getCollection(uri1, "test");
        MongoCollection<Document> test2 = getCollection(uri2, "test");
        test1.drop();
        // 1、insert   x = 1
        test1.insertOne(Document.parse("{'x': 1}"));
        // 2.两种方式查询  -   期望结果一直
        FindIterable<Document> documents = test1.find();
        System.out.println("不加 readPreference=secondary 查询结果 x=1：");
        for (Document document : documents) {
            System.out.println(document);
        }

        FindIterable<Document> documents1 = test2.find();
        System.out.println("加 readPreference=secondary 查询结果 x=1：");
        for (Document document : documents1) {
            System.out.println(document);
        }
        // 3.断掉同步   db.fsyncLock()

        // 4.insert x = 2
        test2.insertOne(Document.parse("{'x': 2}"));

        // 5.两种方式查询， -  期望结果
            // 1）不加 readPreference=secondary 查询出两条结果，
            // 2) 加上 readPreference=secondary 查询出一条数据
        FindIterable<Document> documents3 = test1.find();
        System.out.println("不加 readPreference=secondary 查询结果,期望 x=1, x=2：");
        for (Document document : documents3) {
            System.out.println(document);
        }

        FindIterable<Document> documents4 = test2.find();
        System.out.println("加 readPreference=secondary 查询结果,期望  x=1：");
        for (Document document : documents4) {
            System.out.println(document);
        }

    }

    private static MongoCollection<Document> getCollection(String uri1, String test) {
        MongoDatabase dataBase = getDataBase(uri1);
        return dataBase.getCollection(test);
    }

    private static MongoDatabase getDataBase(String uri) {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(uri));
        MongoDatabase database = mongoClient.getDatabase("MedicalExaminationDB3");
        ReadPreference readPreference = database.getReadPreference();
        return database;
    }

}

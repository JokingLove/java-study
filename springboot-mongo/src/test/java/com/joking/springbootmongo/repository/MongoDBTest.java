package com.joking.springbootmongo.repository;

import com.joking.springbootmongo.entity.Person;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import sun.text.SupplementaryCharacterData;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Log4j2
@SpringBootTest
public class MongoDBTest {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testMongoClient() {
        MongoDatabase database = mongoClient.getDatabase("MedicalExaminationDB");
        MongoCollection<Person> person = database.getCollection("person", Person.class);
        person.insertOne(new Person("王五", 20, null));
    }


    @Test
    public void testMongoTemplate() {
//        Person person = mongoTemplate.insert(new Person("王五", 20, null), "person");
//        log.info(person);
//        Query query = Query.query(where("name").is("张三"));
//        Update update = Update.update("age", 100);
//        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Person.class);
//        log.info(updateResult);

        UnwindOperation unwind = new UnwindOperation(Fields.field("$address"));
        MatchOperation match = new MatchOperation(where("address.province").is("北京"));
        CountOperation count = new CountOperation("count");

        Aggregation aggregation = Aggregation.newAggregation(unwind, match, count);
        AggregationResults<Document> result = mongoTemplate.aggregate(aggregation, "person", Document.class);
        log.info(result.getMappedResults().get(0));

    }


}

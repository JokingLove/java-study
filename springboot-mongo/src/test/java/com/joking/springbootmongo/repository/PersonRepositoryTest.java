package com.joking.springbootmongo.repository;

import com.joking.springbootmongo.entity.Person;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static java.util.Arrays.asList;

@Slf4j
@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoClient mongoClient;

    @Test
    public void testMongoClient() {
        MongoDatabase database = mongoClient.getDatabase("MedicalExaminationDB");
        MongoCollection<Person> person = database.getCollection("person", Person.class);
        person.insertOne(new Person("李四", 20, null));
        person.insertMany(asList(
                new Person("李四", 20, null),
                new Person("王五", 20, null)
        ));
    }

    @Test
    public void testMongoTemplate() {
        Person person = new Person("李四", 20,
                asList(
                        new Person.Address("1", "2", "3", 4),
                        new Person.Address("1", "2", "3", 4),
                        new Person.Address("1", "2", "3", 4)
                ));

        Person person1 = mongoTemplate.insert(person, "person");
        log.info(person1.toString());
    }

    @Test
    public void testSave() {
        Person person = new Person();
        person.setName("张三");
        person.setAge(10);
        person.setAddress(
                asList(
                        new Person.Address("北京", "海淀", "建外", 10)
                )
        );
        Person save = personRepository.save(person);
        log.info(save.toString());
    }

    @Test
    public void findByNameTest() {
        personRepository.findByName("张三")
                .ifPresent(i -> {
                    i.forEach(j -> log.info(j.toString()));
                });
    }

    @Test
    public void findByAddressProvinceTest() {
        personRepository.findByAddressProvince("北京")
                .ifPresent(i -> {
                    i.forEach(j -> log.info(j.toString()));
                });
    }

    @Test
    public void findByNameRegexTest() {
        personRepository.findByNameRegex("三")
                .ifPresent(i -> {
                    i.forEach(j -> log.info(j.toString()));
                });
    }



}

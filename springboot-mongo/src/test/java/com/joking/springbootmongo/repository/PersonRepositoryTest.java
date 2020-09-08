package com.joking.springbootmongo.repository;

import com.joking.springbootmongo.entity.Person;
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

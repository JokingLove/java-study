package com.joking.springbootmongo.repository;

import com.joking.springbootmongo.entity.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends MongoRepository<Person, ObjectId> {

    Optional<List<Person>> findByName(String name);

    Optional<List<Person>> findByAddressProvince(String name);

    Optional<List<Person>> findByNameRegex(String name);
}

package com.joking.test.repository;

import com.joking.test.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author joking
 */
public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> getUserByName(String name);

    Optional<List<User>> getUserByNameLike(String name);

    Optional<List<User>> getUserByAddressProvince(String province);

    Optional<List<User>> getUserByAddressProvinceLike(String province);

}

package com.joking.springjpa.repository;

import com.joking.springjpa.document.MedicalImage;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author joking
 */
@Repository
public class MedicalImageRepository extends SimpleReactiveMongoRepository<MedicalImage, ObjectId> {

    public MedicalImageRepository(@NonNull MongoEntityInformation<MedicalImage, ObjectId> entityInformation, @NonNull ReactiveMongoOperations mongoOperations) {
        super(entityInformation, mongoOperations);
    }

}

package com.jdoodle.repository;

import com.jdoodle.model.FeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FeeCatalogRepository extends MongoRepository<FeeEntity, String> {
    Optional<List<FeeEntity>> findAll();
}
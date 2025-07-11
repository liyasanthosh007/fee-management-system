package com.jdoodle.repository;

import com.jdoodle.entity.FeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeeCatalogRepository extends MongoRepository<FeeEntity, String> {
    List<FeeEntity> findAll();
}
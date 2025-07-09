package com.jdoodle.repository;

import com.jdoodle.model.ReceiptEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReceiptRepository extends MongoRepository<ReceiptEntity, String> {
    Optional<ReceiptEntity> findByOrderId(String orderId);
	
	Optional<ReceiptEntity> save(ReceiptEntity receipt);
}
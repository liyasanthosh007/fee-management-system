package com.jdoodle.repository;

import com.jdoodle.entity.ReceiptEntity;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface ReceiptRepository extends MongoRepository<ReceiptEntity, String> {
    ReceiptEntity findByOrderId(String orderId);
	
	ReceiptEntity save(ReceiptEntity receipt);
}
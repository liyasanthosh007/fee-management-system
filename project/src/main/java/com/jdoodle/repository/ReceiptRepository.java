package com.jdoodle.repository;

import com.jdoodle.entity.ReceiptEntity;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;



public interface ReceiptRepository extends MongoRepository<ReceiptEntity, String> {
    ReceiptEntity findByOrderId(String orderId);
	
	ReceiptEntity save(ReceiptEntity receipt);

    Optional<ReceiptEntity> findByStudentId(String studentId);
}
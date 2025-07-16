package com.jdoodle.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.repository.ReceiptRepository;
import com.jdoodle.service.ReceiptService;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepo;

    public ReceiptEntity getByOrderId(String orderId) {
        return receiptRepo.findByOrderId(orderId);
    }
}
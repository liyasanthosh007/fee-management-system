package com.jdoodle.service.impl;

import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.repository.ReceiptRepository;

public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepo;

    public ReceiptEntity getByOrderId(String orderId) {
        return receiptRepo.findByOrderId(orderId);
    }
}
package com.jdoodle.service.impl;

import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepo;

    public ReceiptEntity getByOrderId(String orderId) {
        return receiptRepository.findByOrderId(orderId);
    }
}
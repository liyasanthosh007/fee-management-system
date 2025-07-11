package com.jdoodle.service;

import com.jdoodle.entity.ReceiptEntity;
import org.springframework.stereotype.Service;

@Service
public interface ReceiptService {
    ReceiptEntity getByOrderId(String orderId);
}
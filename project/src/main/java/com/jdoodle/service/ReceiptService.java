package com.jdoodle.service;

import com.jdoodle.entity.ReceiptEntity;

public interface ReceiptService {
    ReceiptEntity getByOrderId(String orderId);
}
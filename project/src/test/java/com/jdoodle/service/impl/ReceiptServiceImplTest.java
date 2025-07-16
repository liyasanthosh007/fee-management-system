package com.jdoodle.service.impl;

import com.jdoodle.common.PaymentStatus;
import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.repository.ReceiptRepository;
import com.jdoodle.service.implementation.ReceiptServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

class ReceiptServiceImplTest {

    @Mock
    private ReceiptRepository receiptRepository;

    @InjectMocks
    private ReceiptServiceImpl receiptService;

    @Test
    void testGetByOrderId() {
        
        String orderId = "ORDER123";
        String studentId = "S123";
        String feeId = "FEE123";
        Double amount = 2500.0;
        Double paidAmount = 0.0;
        LocalDateTime createdDate = LocalDateTime.now();
        ReceiptEntity mockReceipt = new ReceiptEntity(orderId, studentId, feeId, amount, paidAmount, PaymentStatus.UNPAID, createdDate, createdDate);

        when(receiptRepository.findByOrderId(orderId)).thenReturn(mockReceipt);

        ReceiptEntity result = receiptService.getByOrderId(orderId);

        assertNotNull(result);
        assertEquals(orderId, result.getOrderId());
        assertEquals(feeId, result.getFeeId());
        assertEquals(studentId, result.getStudentId());
        assertEquals(amount, result.getPaidAmount());
        assertEquals(PaymentStatus.UNPAID, result.getPaymentStatus());
        assertEquals(createdDate, result.getCreatedDate());
    }
}

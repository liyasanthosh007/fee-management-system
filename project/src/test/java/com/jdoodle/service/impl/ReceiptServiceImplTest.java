package com.jdoodle.service.impl;

import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.repository.ReceiptRepository;
import com.jdoodle.service.implementation.ReceiptServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

class ReceiptServiceImplTest {

    @Mock
    private ReceiptRepository receiptRepository;

    @InjectMocks
    private ReceiptServiceImpl receiptService;

    @Test
    void testGetByOrderId() {
        
        String orderId = "ORDER123";
        String studentId = "STU123";
        String feeId = "FEE123";
        Double amount = 2500.0;
        String status = "SUCCESS";
        LocalDate createdDate = LocalDate.now();
        ReceiptEntity mockReceipt = new ReceiptEntity(orderId, studentId, feeId, amount, status, createdDate);

        when(receiptRepository.findByOrderId(orderId)).thenReturn(mockReceipt);

        ReceiptEntity result = receiptService.getByOrderId(orderId);

        assertNotNull(result);
        assertEquals(orderId, result.getOrderId());
        assertEquals(feeId, result.getFeeId());
        assertEquals(studentId, result.getStudentId());
        assertEquals(amount, result.getAmountPaid());
        assertEquals(status, result.getStatus());
        assertEquals(createdDate, result.getCreatedDate());
    }
}

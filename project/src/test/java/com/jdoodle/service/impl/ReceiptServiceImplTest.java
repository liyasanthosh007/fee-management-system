package com.jdoodle.service.impl;

import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.repository.ReceiptRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ReceiptServiceImplTest {

    @Mock
    private ReceiptRepository receiptRepository;

    @InjectMocks
    private ReceiptServiceImpl receiptService;

    @Test
    void testGetByOrderId() {
        
        String orderId = "ORDER123";
        ReceiptEntity mockReceipt = new ReceiptEntity();
        mockReceipt.setOrderId(orderId);
        mockReceipt.setAmount(2500.0);

        when(receiptRepository.findByOrderId(orderId)).thenReturn(mockReceipt);

        ReceiptEntity result = receiptService.getByOrderId(orderId);

        assertNotNull(result);
        assertEquals(orderId, result.getOrderId());
        assertEquals(2500.0, result.getAmount(), 0.001);
    }
}

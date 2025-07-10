package com.jdoodle.service.impl;

import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.entity.FeeCatalogEntity;
import com.jdoodle.repository.ReceiptRepository;
import com.jdoodle.repository.FeeCatalogRepository;
import com.jdoodle.request.FeeRequest;
import com.jdoodle.service.PaymentService;
import com.jdoodle.service.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FeeServiceImplTest {

	@InjectMocks
    private FeeServiceImpl feeService;

	@Mock
    private ReceiptRepository receiptRepo;
	
	@Mock
    private FeeCatalogRepository feeCatalogRepo;
	
	@InjectMocks
    private PaymentService paymentService;
	
	@InjectMocks
    private StudentService studentService;

    @Test
    void testCollectFee_success() {
        FeeRequest request = new FeeRequest("stu1", "fee123", 1200.0);
        ReceiptEntity savedReceipt = new ReceiptEntity("order123", "stu1", "fee123", 1200.0, "SUCCESS", LocalDate.now());

        when(receiptRepo.save(any(ReceiptEntity.class))).thenReturn(savedReceipt);

        ReceiptEntity result = feeService.collectFee(request);

        assertNotNull(result);
        assertEquals("stu1", result.getStudenId());
        assertEquals("fee123", result.getFeeId());
        assertEquals(1200.0, result.getAmountPaid());
        assertEquals("SUCCESS", result.getStatus());

    }

    @Test
    void testGetAllFees() {
        List<FeeCatalogEntity> mockFees = Arrays.asList(
            new FeeCatalogEntity("fee1", "Tuition", 1000.0),
            new FeeCatalogEntity("fee2", "Lab", 500.0)
        );

        when(feeCatalogRepo.findAll()).thenReturn(mockFees);

        List<FeeCatalogEntity> result = feeService.getAllFees();

        assertEquals(2, result.size());
        assertEquals("Tuition", result.get(0).getFeeName());
        assertEquals(500.0, result.get(1).getAmount());
    }
}

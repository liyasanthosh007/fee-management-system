package com.jdoodle.service.impl;

import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.entity.FeeEntity;
import com.jdoodle.repository.ReceiptRepository;
import com.jdoodle.repository.FeeCatalogRepository;
import com.jdoodle.model.FeeRequest;
import com.jdoodle.service.StudentService;
import com.jdoodle.service.implementation.FeeServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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
        List<FeeEntity> mockFees = Arrays.asList(
            new FeeEntity("fee1", "Tuition", 1000.0),
            new FeeEntity("fee2", "Lab", 500.0)
        );

        when(feeCatalogRepo.findAll()).thenReturn(mockFees);

        List<FeeEntity> result = feeService.getAllFees();

        assertEquals(2, result.size());
        assertEquals("Tuition", result.get(0).getDescription());
        assertEquals(500.0, result.get(1).getAmount());
    }
}

package com.jdoodle.service.impl;

import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.common.PaymentStatus;
import com.jdoodle.entity.FeeEntity;
import com.jdoodle.repository.ReceiptRepository;
import com.jdoodle.repository.FeeCatalogRepository;
import com.jdoodle.model.FeeRequest;
import com.jdoodle.model.Response;
import com.jdoodle.service.StudentService;
import com.jdoodle.service.implementation.FeeServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    void testCollectFee_FirstTimePartialPayment_Success() {
        // Arrange
        String studentId = "S101";
        String feeId = "FEE001";
        double amountToPay = 500.0;

        FeeRequest request = new FeeRequest(studentId, feeId, amountToPay);

        FeeEntity fee = new FeeEntity(feeId, "Tuition", "Quarter Fee", 1000.0, "ACTIVE", "2025-07-01");

        when(feeCatalogRepo.findById(feeId)).thenReturn(Optional.of(fee));
        when(receiptRepo.findByStudentId(studentId)).thenReturn(Optional.empty());

        // Capture the saved receipt
        ArgumentCaptor<ReceiptEntity> captor = ArgumentCaptor.forClass(ReceiptEntity.class);
        when(receiptRepo.save(any(ReceiptEntity.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        Response response = feeService.collectFee(request);

        // Assert
        assertEquals("400", response.getStatus());
        assertTrue(response.getDescription().contains("Payment successful"));
        assertTrue(response.getData().contains(studentId));
        assertTrue(response.getData().contains(PaymentStatus.PARTIALLY_PAID.name()));

        verify(receiptRepo).save(captor.capture());
        ReceiptEntity savedReceipt = captor.getValue();
        assertEquals(500.0, savedReceipt.getPaidAmount());
        assertEquals(PaymentStatus.PARTIALLY_PAID, savedReceipt.getPaymentStatus());
    }

    @Test
    void testCollectFee_Overpayment_ReturnsErrorResponse() {
        // Arrange
        String studentId = "S1";
        String feeId = "FEE1";

        FeeRequest request = new FeeRequest(studentId, feeId, 1200.0);

        FeeEntity fee = new FeeEntity(feeId, "Tuition", "Annual Fee", 1000.0, "1", "2025-07-01");
        ReceiptEntity receipt = new ReceiptEntity(
                "ORD1", feeId, studentId,
                1000.0, 0.0, PaymentStatus.UNPAID,
                LocalDateTime.now(), null
        );

        when(feeCatalogRepo.findById(feeId)).thenReturn(Optional.of(fee));
        when(receiptRepo.findByStudentId(studentId)).thenReturn(Optional.of(receipt));

        Response response = feeService.collectFee(request);

        assertEquals("404", response.getStatus());
        assertTrue(response.getDescription().contains("exceeds total fee"));
    }

    @Test
    void testCollectFee_FeeNotFound() {
        
        String studentId = "S2";
        String feeId = "FEE2";

        FeeRequest request = new FeeRequest(studentId, feeId, 300.0);

        when(feeCatalogRepo.findById(feeId)).thenReturn(Optional.empty());

        Response response = feeService.collectFee(request);

        assertEquals("404", response.getStatus());
        assertEquals("Fee details not found", response.getDescription());
    }

    @Test
    void testGetAllFees() {
        List<FeeEntity> mockFees = Arrays.asList(
            new FeeEntity("1", "fee1", "Tuition", 1000.0, "1",  "10/07/2025"),
            new FeeEntity("2", "fee2", "Lab", 500.0, "1", "10/07/2025")
        );

        when(feeCatalogRepo.findAll()).thenReturn(mockFees);

        List<FeeEntity> result = feeService.getAllFees();

        assertEquals(2, result.size());
        assertEquals("Tuition", result.get(0).getDescription());
        assertEquals(500.0, result.get(1).getAmount());
    }
}

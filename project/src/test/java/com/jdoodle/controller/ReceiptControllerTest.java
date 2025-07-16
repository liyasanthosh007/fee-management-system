package com.jdoodle.controller;

import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.service.ReceiptService;
import com.jdoodle.common.PaymentStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

@WebMvcTest(ReceiptController.class)
class ReceiptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceiptService receiptService;

    @Test
    void testGetByOrderId_found() throws Exception {
        String orderId = "ORDER123";
        String feeId = "FEE123";
        String studentId = "STU123";
        Double amount = 1500.0;
        Double paidAmount = 1500.0;
        PaymentStatus status = PaymentStatus.UNPAID;
        LocalDateTime createdDate= LocalDateTime.now();
        ReceiptEntity mockReceipt = new ReceiptEntity(orderId, feeId, studentId, amount, paidAmount, status, createdDate, createdDate);

        when(receiptService.getByOrderId(orderId)).thenReturn(mockReceipt);

        mockMvc.perform(get("/receipt/getByOrderId/" + orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(orderId))
                .andExpect(jsonPath("$.feeId").value(feeId))
                .andExpect(jsonPath("$.studentId").value(studentId))
                .andExpect(jsonPath("$.amount").value(1500.0))
                .andExpect(jsonPath("$.status").value(status))
                .andExpect(jsonPath("$.createdDate").value(createdDate));
    }
	
	@Test
    void testGetByOrderId_notFound() throws Exception {
        String orderId = "INVALID";

        when(receiptService.getByOrderId(orderId)).thenReturn(null);

        mockMvc.perform(get("/receipt/getByOrderId/{orderId}", orderId))
                .andExpect(status().isNotFound());
    }
}

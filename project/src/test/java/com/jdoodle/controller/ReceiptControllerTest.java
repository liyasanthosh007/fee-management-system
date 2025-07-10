package com.jdoodle.controller;

import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.service.ReceiptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FeeController.class)
class ReceiptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceiptService receiptService;

    @Test
    void testGetByOrderId_found() throws Exception {
        String orderId = "ORDER123";
        ReceiptEntity mockReceipt = new ReceiptEntity();
        mockReceipt.setOrderId(orderId);
        mockReceipt.setAmount(1500.0);

        when(receiptService.getByOrderId(orderId)).thenReturn(mockReceipt);

        mockMvc.perform(get("/receipt/getByOrderId/" + orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(orderId))
                .andExpect(jsonPath("$.amount").value(1500.0));
    }
	
	@Test
    void testGetByOrderId_notFound() throws Exception {
        String orderId = "INVALID";

        when(receiptService.getByOrderId(orderId)).thenReturn(null);

        mockMvc.perform(get("/receipt/getByOrderId/{orderId}", orderId))
                .andExpect(status().isNotFound());
    }
}

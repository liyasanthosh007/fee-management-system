package com.jdoodle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.entity.FeeEntity;
import com.jdoodle.request.FeeRequest;
import com.jdoodle.service.FeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FeeController.class)
class FeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeeService feeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllFees() throws Exception {
        List<FeeEntity> mockFees = Arrays.asList(
            new FeeEntity("fee1", "Tuition", 1000.0),
            new FeeEntity("fee2", "Library", 200.0)
        );

        Mockito.when(feeService.getAllFees()).thenReturn(mockFees);

        mockMvc.perform(get("/fee/getAllFees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].feeName").value("Tuition"));
    }

    @Test
    void testCollectFee() throws Exception {
        FeeRequest request = new FeeRequest("student123", "fee123", 1500.0);

        ReceiptEntity receipt = new ReceiptEntity(
                "order123",
                "fee123",
                "student123",
                1500.0,
                "SUCCESS",
                LocalDate.of(2024, 7, 9)
        );

        Mockito.when(feeService.collectFee(any(FeeRequest.class))).thenReturn(receipt);

        mockMvc.perform(post("/fee/collect")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value("order123"))
                .andExpect(jsonPath("$.feeId").value("fee123"))
                .andExpect(jsonPath("$.studenId").value("student123"))
                .andExpect(jsonPath("$.amountPaid").value(1500.0))
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.createdDate").value("2024-07-09"));
    }
}

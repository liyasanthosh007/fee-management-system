package com.jdoodle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdoodle.entity.FeeEntity;
import com.jdoodle.model.FeeRequest;
import com.jdoodle.model.Response;
import com.jdoodle.common.PaymentStatus;
import com.jdoodle.service.FeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

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
            new FeeEntity("1","fee1", "Tuition", 1000.0, "1","10/07/2025"),
            new FeeEntity("2","fee2", "Library", 200.0, "1","10/07/2025")
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
                Response mockResponse = new Response(
                        PaymentStatus.PARTIALLY_PAID.name(),
                        "Payment successful",
                        "ORD123"
                );
        
                Mockito.when(feeService.collectFee(Mockito.any(FeeRequest.class)))
                       .thenReturn(mockResponse);
        
                mockMvc.perform(post("/api/fees/collect")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status").value("PARTIALLY_PAID"))
                    .andExpect(jsonPath("$.description").value("Payment successful"))
                    .andExpect(jsonPath("$.data").value("ORD123"));

        
    }
}

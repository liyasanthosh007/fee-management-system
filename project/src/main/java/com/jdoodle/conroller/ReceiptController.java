package com.jdoodle.controller;

import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/getByOrderId/{orderId}")
    public ResponseEntity<ReceiptEntity> getByOrderId(@PathVariable String orderId) {
        ReceiptEntity receipt = receiptService.getByOrderId(orderId);
        if (receipt == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(receipt);
    }
   
}
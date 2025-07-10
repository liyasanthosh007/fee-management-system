package com.jdoodle.controller;

import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.entity.FeeEntity;
import com.jdoodle.service.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fee")
public class FeeController {

    @Autowired
    private FeeService feeService;

    @GetMapping("/getAllFees")
    public ResponseEntity<List<FeeEntity>> getAllFees() {
        return ResponseEntity.ok(feeService.getAllFees());
    }
    
    @PostMapping("/collect")
    public ResponseEntity<ReceiptEntity> collectFee(@RequestBody FeeRequest request) {
        return ResponseEntity.ok(feeService.collectFee(request));
    }
}
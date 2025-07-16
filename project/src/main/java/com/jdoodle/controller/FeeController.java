package com.jdoodle.controller;

import com.jdoodle.model.FeeRequest;
import com.jdoodle.model.Response;
import com.jdoodle.entity.FeeEntity;
import com.jdoodle.service.FeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Response> collectFee(@RequestBody FeeRequest request) {
        return ResponseEntity.ok(feeService.collectFee(request));
    }
}
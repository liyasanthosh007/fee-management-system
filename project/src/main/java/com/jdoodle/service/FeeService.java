package com.jdoodle.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.entity.FeeEntity;
import com.jdoodle.model.FeeRequest;

@Service
public interface FeeService {
    public ReceiptEntity collectFee(FeeRequest req);
    public List<FeeEntity> getAllFees();
}
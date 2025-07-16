package com.jdoodle.service;

import java.util.List;

import com.jdoodle.entity.FeeEntity;
import com.jdoodle.model.FeeRequest;
import com.jdoodle.model.Response;

public interface FeeService {
    public Response collectFee(FeeRequest req);
    public List<FeeEntity> getAllFees();
}
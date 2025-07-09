package com.jdoodle.service;

@Service
public interface FeeService {
    public ReceiptEntity collectFee(FeeRequest req);
    public List<FeeCatalogEntity> getAllFees();
}
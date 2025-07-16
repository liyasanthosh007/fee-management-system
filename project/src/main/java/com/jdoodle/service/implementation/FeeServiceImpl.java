package com.jdoodle.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdoodle.common.PaymentStatus;
import com.jdoodle.entity.FeeEntity;
import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.model.FeeRequest;
import com.jdoodle.model.Response;
import com.jdoodle.repository.FeeCatalogRepository;
import com.jdoodle.repository.ReceiptRepository;
import com.jdoodle.service.FeeService;

@Service
public class FeeServiceImpl implements FeeService {

    @Autowired
    private ReceiptRepository receiptRepo;
	
	@Autowired
    private FeeCatalogRepository feeCatalogRepo;

    public Response collectFee(FeeRequest req) {
        String studentId = req.getStudentId();
        String feeId = req.getFeeId();
        
		Response response = new Response();
        Optional<FeeEntity> optFeeEntity = feeCatalogRepo.findById(feeId);
        if(optFeeEntity.isEmpty()){
            response.setStatus("404");
            response.setDescription("Fee details not found");
            response.setData(req.toString());
        } else{
            FeeEntity feeEntity = optFeeEntity.get();
            
            ReceiptEntity receipt = receiptRepo.findByStudentId(studentId)
        .orElseGet(() -> {
            ReceiptEntity newReceipt = new ReceiptEntity();
            newReceipt.setFeeId(feeId);
            newReceipt.setStudentId(studentId);
            newReceipt.setTotalAmount(feeEntity.getAmount()); 
            newReceipt.setPaidAmount(0.0);
            newReceipt.setCreatedDate(LocalDateTime.now());
            newReceipt.setPaymentStatus(PaymentStatus.UNPAID);
            return newReceipt;
        });

        double newPaid = receipt.getPaidAmount() + req.getAmount();
        if (newPaid > receipt.getTotalAmount()) {
            response.setStatus("404");
            response.setDescription("Payment exceeds total fee");
            response.setData(req.toString());
            return response;
        }

        receipt.setPaidAmount(newPaid);
        receipt.setUpdatedDate(LocalDateTime.now());

        // Update payment status based on paid amount
        if (newPaid == receipt.getTotalAmount()) {
            receipt.setPaymentStatus(PaymentStatus.PAID);
        } else if (newPaid > 0) {
            receipt.setPaymentStatus(PaymentStatus.PARTIALLY_PAID);
        } else {
            receipt.setPaymentStatus(PaymentStatus.UNPAID);
        }

        receipt = receiptRepo.save(receipt);
        response.setStatus("400");
            response.setDescription("Payment successful");
            response.setData(receipt.toString());
        }
        return response;
        
    }

    public List<FeeEntity> getAllFees(){
        List<FeeEntity> feeCatalogEntityList = feeCatalogRepo.findAll();
        return feeCatalogEntityList;
    }
	
}
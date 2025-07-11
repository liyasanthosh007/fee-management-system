package com.jdoodle.service.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jdoodle.entity.FeeEntity;
import com.jdoodle.entity.ReceiptEntity;
import com.jdoodle.model.FeeRequest;
import com.jdoodle.repository.FeeCatalogRepository;
import com.jdoodle.repository.ReceiptRepository;
import com.jdoodle.service.FeeService;

public class FeeServiceImpl implements FeeService {

    @Autowired
    private ReceiptRepository receiptRepo;
	
	@Autowired
    private FeeCatalogRepository feeCatalogRepo;

    public ReceiptEntity collectFee(FeeRequest req) {
        String studentId = req.getStudentId();
        String feeId = req.getFeeId();
        String paymentStatus = "SUCCESS";
		
        /**
			logic to make the payment
			
			StudentEntity student = studentService.getById(studentId);
			
			paymentStatus = processPayment(feeId, student, req.getAmount());
		**/

        ReceiptEntity receipt = new ReceiptEntity(null, feeId, studentId, req.getAmount(), paymentStatus, LocalDate.now());
        receipt = receiptRepo.save(receipt);
        return receipt;
    }

    public List<FeeEntity> getAllFees(){
        List<FeeEntity> feeCatalogEntityList = feeCatalogRepo.findAll();
        return feeCatalogEntityList;
    }
	
	/**
	private String processPayment(String feeId, StudentEntity student, Double amount){
		Logic to make the actual payment
		if(payment success)
			return "SUCCESS";
		else
			returm "FAILED";
	}
	**/
}
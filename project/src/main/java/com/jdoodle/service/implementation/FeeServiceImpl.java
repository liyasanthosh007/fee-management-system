package com.jdoodle.service.impl;

public class FeeServiceImpl implements FeeService {

    @Autowired
    private ReceiptRepository receiptRepo;
	
	@Autowired
    private FeeCatalogRepository feeCatalogRepo;
    
    @Autowired
    private PaymentService paymentService;
	
	@Autowired
    private StudentService studentService;

    public ReceiptEntity collectFee(FeeRequest req) {
        String studentId = req.getStudentId();
        String feeId = req.getFeeId();
        String paymentStatus = "SUCCESS";
		
        /**
			logic to make the payment
			
			StudentEntity student = studentService.getById(studentId);
			
			paymentStatus = processPayment(feeId, student, req.getAmount());
		**/

        ReceiptEntity receipt = new ReceiptEntity(null, studentId, feeId, req.getAmount(), paymentStatus, LocalDate.now());
        receipt = receiptRepo.save(receipt);
        return receipt;
    }

    public List<FeeCatalogEntity> getAllFees(){
        List<FeeCatalogEntity> feeCatalogEntityList = feeCatalogRepo.findAll();
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
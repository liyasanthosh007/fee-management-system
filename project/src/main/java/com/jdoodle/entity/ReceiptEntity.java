package com.jdoodle.entity;

import lombok.*;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jdoodle.common.PaymentStatus;


@Document(collection = "receipts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptEntity {
    @Id
    private String orderId;
	private String feeId; //foreign key from Fee table
    private String studentId; //foreign key from Student table
    private Double totalAmount;
    private Double paidAmount;
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;
    private LocalDateTime  createdDate;
    private LocalDateTime  updatedDate;
    
}
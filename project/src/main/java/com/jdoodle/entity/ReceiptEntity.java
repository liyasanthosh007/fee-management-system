package com.jdoodle.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "receipts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptEntity {
    @Id
    private String orderId;
	private String feeId; //foreign key from Fee table
    private String studenId; //foreign key from Student table
    private double amountPaid;
    private String status;
    private LocalDate createdDate;
}
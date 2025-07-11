package com.jdoodle.entity;

import lombok.*;

import java.time.LocalDate;

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
    private String studentId; //foreign key from Student table
    private double amountPaid;
    private String status;
    private LocalDate createdDate;
	public ReceiptEntity(String orderId, String feeId, String studentId,  double amountPaid, String status, LocalDate createdDate) {
    this.orderId = orderId;
    this.feeId = feeId;
    this.studentId = studentId;
    this.amountPaid = amountPaid;
    this.status= status;
    this.createdDate = createdDate;
	}
	public void setStudentId(String studentId) {
    this.studentId = studentId;
	}
  public void setFeeId(String feeId) {
    this.feeId = feeId;
  }
  public void setAmountPaid(double amountPaid) {
    this.amountPaid = amountPaid;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public Object getOrderId() {
  return this.orderId;
  }
  public String getStudentId() {
    return this.studentId;
  }
  public String getFeeId() {
    return this.feeId;
  }
  public Double getAmountPaid() {
    return this.amountPaid;
  }
  public String getStatus() {
    return this.status;
  }
  public LocalDate getCreatedDate() {
    return this.createdDate;
  }

}
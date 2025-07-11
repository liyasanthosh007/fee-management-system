package com.jdoodle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeRequest {
    private String studentId;
    private String feeId;
    private double amount;
	public FeeRequest(String studentId, String feeId, double amount) {
    this.studentId = studentId;
    this.feeId = feeId;
    this.amount = amount;
	}
	public String getStudentId() {
		return this.studentId;
	}
	public String getFeeId() {
		return this.feeId;
	}
  public Double getAmount() {
    return this.amount;
  }
}
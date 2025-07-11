package com.jdoodle.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeEntity {
    public FeeEntity(String category, String description, double amount) {
      this.category= category;
      this.description = description;
      this.amount = amount;
    }
    @Id
    private String id;
	private String category;
    private String description;
    private double amount;
    private String status;
    private String createdDate;
	
  public String getCategory() {
		return this.category;
	}
	public String getDescription() {
		return this.description;
	}
    public Double getAmount() {
        return this.amount;
    }

    public String getStatus() {
    return this.status;
  }

  public String getCreatedDate() {
    return this.createdDate;
  }
}
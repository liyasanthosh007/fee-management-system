package com.jdoodle.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeEntity {
    @Id
    private String id;
	private String category;
    private String description;
    private double amount;
    private String status;
    private String createdDate;
    private String classId;
}
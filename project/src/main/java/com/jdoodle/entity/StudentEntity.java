package com.jdoodle.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {
    @Id
    private String id;
	private String firstName;
    private String lastName;
	private LocalDate dob;
    private String status;
    private LocalDate createdDate;
}
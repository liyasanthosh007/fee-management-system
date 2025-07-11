package com.jdoodle.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jdoodle.entity.StudentEntity;
import com.jdoodle.model.Response;

@Service
public interface StudentService {
	public StudentEntity save(StudentEntity student);
	public List<StudentEntity> getAllStudents();
    public StudentEntity getById(String studentId);
	public Response deleteById(String studentId);
}
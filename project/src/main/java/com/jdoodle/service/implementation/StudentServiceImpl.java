package com.jdoodle.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.jdoodle.entity.StudentEntity;
import com.jdoodle.model.Response;
import com.jdoodle.service.StudentService;

public class StudentServiceImpl implements StudentService {
 //Dummy student service
    public StudentEntity getById(String studentId) {
        //Dummy method to get student by id
        StudentEntity student = new StudentEntity();
        return student;
	}
	
	public StudentEntity save(StudentEntity student){
        //Dummy method to save student record
        return student;
	}
	
	public List<StudentEntity> getAllStudents(){
        //Dummy method to get all students
        List<StudentEntity> student = new ArrayList<StudentEntity>();
        return student;
	}
 
	public Response deleteById(String studentId){
        //Dummy method to delete student by id
        return new Response();
	}

}
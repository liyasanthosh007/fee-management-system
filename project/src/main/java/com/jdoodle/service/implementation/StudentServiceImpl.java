package com.jdoodle.service.impl;

public class StudentServiceImpl implements StudentService {
 //Dummy student service
    public StudentEntity getById(String studentId) {
        //Dummy method to get student by id
        StudentEntity student = new StudentEntity();
        return student;
	}
	
	public StudentEntity save(StudentEntity student){
        //Dummy method to save student record
        StudentEntity student = new StudentEntity();
        return student;
	}
	
	public List<StudentEntity> getAllStudents(){
        //Dummy method to get all students
        StudentEntity student = new StudentEntity();
        return student;
	}
 
	public Response deleteById(String studentId){
        //Dummy method to delete student by id
	}
}
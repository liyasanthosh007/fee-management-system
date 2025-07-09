package com.jdoodle.service;

@Service
public interface StudentService {
	public StudentEntity save(StudentEntity student);
	public List<StudentEntity> getAllStudents();
    public StudentEntity getById(String studentId);
	public void deleteById(String studentId);
}
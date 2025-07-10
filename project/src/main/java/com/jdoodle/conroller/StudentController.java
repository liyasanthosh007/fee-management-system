package com.jdoodle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public StudentEntity addStudent(@RequestBody StudentEntity student) {
        return studentService.save(student);
    }

    @GetMapping("/getAllStudents")
    public List<StudentEntity> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("getStudentById/{id}")
    public StudentEntity getStudentById(@PathVariable String id) {
        return studentService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public Response deleteStudent(@PathVariable String id) {
        return studentService.deleteById(id);
    }
}
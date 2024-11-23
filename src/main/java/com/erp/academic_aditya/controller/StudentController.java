package com.erp.academic_aditya.controller;

import com.erp.academic_aditya.dto.StudentRequest;
import com.erp.academic_aditya.entity.Student;
import com.erp.academic_aditya.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    // Create a new student
    @PostMapping
    public String saveStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    // Retrieve a student by ID
    @GetMapping("/{id}")
    public String getStudentById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        return studentService.getStudentById(id, token);
    }

    // Update an existing student
    @PutMapping("/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest, @RequestHeader("Authorization") String token) {
        return studentService.updateStudent(id, studentRequest, token);
    }

    // Delete a student by ID
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        return studentService.deleteStudent(id, token);
    }
}

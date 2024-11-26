package com.erp.academic_aditya.controller;

import com.erp.academic_aditya.dto.LoginRequest;
import com.erp.academic_aditya.dto.StudentRequest;
import com.erp.academic_aditya.entity.Student;
import com.erp.academic_aditya.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(studentService.loginChecking(request));
    }


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


}

package com.erp.academic_aditya.controller;

import com.erp.academic_aditya.dto.AllBillResponse;
import com.erp.academic_aditya.dto.LoginRequest;
import com.erp.academic_aditya.dto.LoginResponse;
import com.erp.academic_aditya.dto.StudentRequest;
import com.erp.academic_aditya.service.StudentBillsService;
import com.erp.academic_aditya.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private final StudentService studentService;
    private final StudentBillsService studentBillsService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
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

    // Fetch bills by student ID
    @GetMapping("/student/{studentId}")
    public List<AllBillResponse> getBillsByStudentId(@PathVariable Long studentId, @RequestHeader("Authorization") String token) {
        return studentBillsService.getBillsByStudentId(studentId, token);
    }

}
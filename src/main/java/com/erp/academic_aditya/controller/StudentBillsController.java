package com.erp.academic_aditya.controller;

import com.erp.academic_aditya.entity.StudentBills;
import com.erp.academic_aditya.service.StudentBillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-bills")
public class StudentBillsController {

    @Autowired
    private StudentBillsService studentBillsService;

    // Fetch all student bills
    @GetMapping
    public List<StudentBills> getAllStudentBills(@RequestHeader("Authorization") String token) {
        return studentBillsService.getAllStudentBills(token);
    }

    // Fetch bills by student ID
    @GetMapping("/student/{studentId}")
    public List<StudentBills> getBillsByStudentId(@PathVariable Long studentId, @RequestHeader("Authorization") String token) {
        return studentBillsService.getBillsByStudentId(studentId, token);
    }

    // Assign a bill to a student
    @PostMapping
    public StudentBills assignBillToStudent(@RequestBody StudentBills studentBill, @RequestHeader("Authorization") String token) {
        return studentBillsService.saveStudentBill(studentBill, token);
    }


}

//package com.erp.academic_aditya.controller;
//
//import com.erp.academic_aditya.entity.StudentPayment;
//import com.erp.academic_aditya.service.StudentPaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/student-payments")
//public class StudentPaymentController {
//
//    @Autowired
//    private StudentPaymentService studentPaymentService;
//
//    @GetMapping
//    public List<StudentPayment> getAllStudentPayments(@RequestHeader("Authorization") String token) {
//        return studentPaymentService.getAllStudentPayments(token);
//    }
//
//    @GetMapping("/student/{studentId}")
//    public List<StudentPayment> getPaymentsByStudentId(@PathVariable Long studentId, @RequestHeader("Authorization") String token) {
//        return studentPaymentService.getPaymentsByStudentId(studentId, token);
//    }
//
//    @PostMapping
//    public StudentPayment addPayment(@RequestBody StudentPayment studentPayment, @RequestHeader("Authorization") String token) {
//        return studentPaymentService.saveStudentPayment(studentPayment, token);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteStudentPayment(@PathVariable Long id, @RequestHeader("Authorization") String token) {
//        studentPaymentService.deleteStudentPayment(id, token);
//    }
//}

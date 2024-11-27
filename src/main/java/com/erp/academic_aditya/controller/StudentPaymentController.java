package com.erp.academic_aditya.controller;

import com.erp.academic_aditya.dto.PaymentHistoryResponse;
import com.erp.academic_aditya.dto.PaymentRequest;
import com.erp.academic_aditya.service.StudentPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class StudentPaymentController {

    @Autowired
    private final StudentPaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> handlePayment(
            @RequestBody PaymentRequest paymentRequest) {
        paymentService.processPayment(paymentRequest);
        return ResponseEntity.ok("Payment processed successfully");
    }

    @GetMapping
    public ResponseEntity<List<PaymentHistoryResponse>> getPaymentHistory(@RequestHeader("student_id") Long studentId) {
        List<PaymentHistoryResponse> paymentHistory = paymentService.getPaymentHistoryForStudent(studentId);
        return ResponseEntity.ok(paymentHistory);
    }
}
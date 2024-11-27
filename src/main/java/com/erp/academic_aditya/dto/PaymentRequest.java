package com.erp.academic_aditya.dto;

public record PaymentRequest(
        Long studentId,
        Long billId,
        Double amount ,    // Amount paid
        boolean useTotal,
        Double totalDue
) {
}
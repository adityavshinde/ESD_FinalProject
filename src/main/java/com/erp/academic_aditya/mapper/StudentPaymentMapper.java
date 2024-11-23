/*
package com.erp.academic_aditya.mapper;

import com.erp.academic_aditya.dto.StudentPaymentRequest;
import com.erp.academic_aditya.dto.StudentPaymentResponse;
import com.erp.academic_aditya.entity.StudentPayment;
import org.springframework.stereotype.Service;

@Service
public class StudentPaymentMapper {

    public StudentPayment toEntity(StudentPaymentRequest request) {
        return StudentPayment.builder()
                .studentId(request.studentId())
                .description(request.description())
                .amount(request.amount())
                .paymentDate(request.paymentDate())
                .billId(request.billId())
                .build();
    }

    public StudentPaymentResponse toStudentPaymentResponse(StudentPayment payment) {
        return new StudentPaymentResponse(
                payment.getId(),
                payment.getStudentId(),
                payment.getDescription(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getBillId()
        );
    }
}
*/
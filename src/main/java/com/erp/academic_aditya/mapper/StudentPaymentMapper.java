package com.erp.academic_aditya.mapper;

import com.erp.academic_aditya.dto.StudentPaymentRequest;
import com.erp.academic_aditya.dto.StudentPaymentResponse;
import com.erp.academic_aditya.entity.Bills;
import com.erp.academic_aditya.entity.Student;
import com.erp.academic_aditya.entity.StudentPayment;
import org.springframework.stereotype.Service;

@Service
public class StudentPaymentMapper {

    public StudentPayment toEntity(Student student, Bills bill) {
        return StudentPayment.builder()
                .student(student)
                .bill(bill)
                .build();
    }

//    public StudentPaymentResponse toStudentPaymentResponse(StudentPayment payment) {
//        return new StudentPaymentResponse(
//                payment.getId(),
//                payment.getStudentId(),
//                payment.getDescription(),
//                payment.getAmount(),
//                payment.getPaymentDate(),
//                payment.getBillId()
//        );
//    }
}
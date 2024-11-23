package com.erp.academic_aditya.service;

import com.erp.academic_aditya.entity.StudentPayment;
import com.erp.academic_aditya.helper.JwtHelper;
import com.erp.academic_aditya.repo.StudentPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentPaymentService {

    private StudentPaymentRepository studentPaymentRepository;

    private JwtHelper jwtHelper;

    public List<StudentPayment> getAllStudentPayments(String token) {
        if (headerChecking(token)) return null;
        return studentPaymentRepository.findAll();
    }

    public List<StudentPayment> getPaymentsByStudentId(Long studentId, String token) {
        if (headerChecking(token)) return null;
        return studentPaymentRepository.findByStudent_StudentId(studentId);
    }

    public StudentPayment saveStudentPayment(StudentPayment studentPayment, String token) {
        if (headerChecking(token)) return null;
        return studentPaymentRepository.save(studentPayment);
    }

    private boolean headerChecking(String receivedToken) {
        String token = receivedToken.substring(7);
        return jwtHelper.validateToken(token);
    }
}

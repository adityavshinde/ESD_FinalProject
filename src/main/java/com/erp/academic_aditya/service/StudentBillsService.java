package com.erp.academic_aditya.service;

import com.erp.academic_aditya.entity.StudentBills;
import com.erp.academic_aditya.helper.JwtHelper;
import com.erp.academic_aditya.repo.StudentBillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentBillsService {

    @Autowired
    private StudentBillsRepository studentBillsRepository;

    @Autowired
    private JwtHelper jwtHelper;

    public List<StudentBills> getAllStudentBills(String token) {
        if (headerChecking(token)) return null;
        return studentBillsRepository.findAll();
    }

    public List<StudentBills> getBillsByStudentId(Long studentId, String token) {
        if (headerChecking(token)) return null;
        return studentBillsRepository.findByStudent_StudentId(studentId);
    }

    public StudentBills saveStudentBill(StudentBills studentBill, String token) {
        if (headerChecking(token)) return null;
        return studentBillsRepository.save(studentBill);
    }


    private boolean headerChecking(String receivedToken) {
        String token = receivedToken.substring(7);
        return jwtHelper.validateToken(token);
    }
}

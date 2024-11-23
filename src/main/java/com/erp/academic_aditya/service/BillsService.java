package com.erp.academic_aditya.service;

import com.erp.academic_aditya.dto.BillsRequest;
import com.erp.academic_aditya.entity.Bills;
import com.erp.academic_aditya.entity.Student;
import com.erp.academic_aditya.entity.StudentBills;
import com.erp.academic_aditya.helper.JwtHelper;
import com.erp.academic_aditya.repo.BillsRepository;
import com.erp.academic_aditya.repo.StudentBillsRepository;
import com.erp.academic_aditya.repo.StudentRepository;
import com.erp.academic_aditya.dto.BillsResponse;
import com.erp.academic_aditya.mapper.BillsMapper;
import com.erp.academic_aditya.mapper.StudentBillsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BillsService {

    @Autowired
    private BillsRepository billsRepository;
    private JwtHelper jwtHelper;
    private final BillsMapper billsMapper;
    private final StudentRepository studentRepository;
    private final StudentBillsRepository studentBillsRepository;
    private final StudentBillsMapper studentBillsMapper;

    public List<Bills> getAllBills(String token) {
        if (!headerChecking(token)) return null;
        return billsRepository.findAll();
    }

    public Bills getBillById(Long id, String token) {
        if (!headerChecking(token)) return null;
        return billsRepository.findById(id).orElse(null);
    }

    public BillsResponse addBill(BillsRequest billsRequest) {

//        return billsRepository.save(bill);
        // Map BillRequest to Bills entity

        Bills bill = billsMapper.toEntity(billsRequest);
        Bills savedBill = billsRepository.save(bill);

        // Fetch the student entity using the studentId from BillRequest
        Student student = studentRepository.findByStudentId(billsRequest.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Map student-bill association and save
        StudentBills studentBill = studentBillsMapper.toEntity(student, savedBill);
        studentBillsRepository.save(studentBill);

        return new BillsResponse(savedBill.getId(), "Bill created and assigned to student successfully.");
    }

    public Bills updateBill(Long id, Bills updatedBill, String token) {
        if (!headerChecking(token)) return null;
        Bills existingBill = billsRepository.findById(id).orElse(null);
        if (existingBill != null) {
            existingBill.setDescription(updatedBill.getDescription());
            existingBill.setAmount(updatedBill.getAmount());
            existingBill.setBillDate(updatedBill.getBillDate());
            existingBill.setDeadline(updatedBill.getDeadline());
            return billsRepository.save(existingBill);
        }
        return null;
    }

    private boolean headerChecking(String receivedToken) {
        String token = receivedToken.substring(7); // Extract token from "Bearer {token}"
        return jwtHelper.validateToken(token);
    }
}

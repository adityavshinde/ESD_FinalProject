package com.erp.academic_aditya.service;

import com.erp.academic_aditya.dto.AllBillResponse;
import com.erp.academic_aditya.entity.Bills;
import com.erp.academic_aditya.entity.StudentBills;
import com.erp.academic_aditya.entity.StudentPayment;
import com.erp.academic_aditya.helper.JwtHelper;
import com.erp.academic_aditya.mapper.BillsMapper;
import com.erp.academic_aditya.repo.StudentBillsRepository;
import com.erp.academic_aditya.repo.StudentPaymentRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentBillsService {

    @Autowired
    private final StudentBillsRepository studentBillsRepository;
    private final StudentPaymentRepository studentPaymentRepository;
    private final BillsMapper billsMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtHelper jwtHelper;

    public List<StudentBills> getAllStudentBills(String token) {
        if (!headerChecking(token)) {
            return studentBillsRepository.findAll();
        }
        return null;
    }

    public List<AllBillResponse> getBillsByStudentId(Long studentId, String token) {
        if (headerChecking(token)) {
            // Fetch all bill IDs associated with the student
            List<StudentBills> studentBills = studentBillsRepository.findByStudent_StudentId(studentId);



            // Extract the associated bills from the StudentBills entities
            List<Bills> bills = studentBills.stream()
                    .map(StudentBills::getBill) // Fetch the Bills entity
                    .toList();

            // Iterate through the bills, calculate current due, and map to response
            List<AllBillResponse> allBillResponses = bills.stream()
                    .filter(bill -> {
                        // Fetch all payments for the current bill and student
                        List<StudentPayment> payments = studentPaymentRepository.findByBillId(bill);

                        // Calculate the total payments for this bill
                        Double totalPaid = payments.stream()
                                .mapToDouble(StudentPayment::getAmount)
                                .sum();

                        // Check if the total payments are less than the bill amount
                        return totalPaid < bill.getAmount(); // Include only bills that are not fully paid
                    })
                    .map(bill -> {
                        // Fetch all payments for the current bill
                        List<StudentPayment> payments = studentPaymentRepository.findByBillId(bill);

                        // Calculate the total payments for this bill
                        Double totalPaid = payments.stream()
                                .mapToDouble(StudentPayment::getAmount)
                                .sum();

                        // Calculate the current due
                        Double currentDue = bill.getAmount() - totalPaid;

                        // Map each bill to a response with current due
                        return billsMapper.toAllBillResponse(bill, currentDue);
                    }) // Map each bill to a response
                    .collect(Collectors.toList());

            return allBillResponses;
        }
        return null;
    }

    public StudentBills saveStudentBill(StudentBills studentBill, String token) {
        if (!headerChecking(token)) {
            return studentBillsRepository.save(studentBill);
        }
        return null;
    }

    private boolean headerChecking(String receivedToken) {
        try {
            String token = receivedToken.substring(7);
            return jwtHelper.validateToken(token);
        } catch (Exception e) {
            System.err.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }
}

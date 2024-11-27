package com.erp.academic_aditya.service;

import com.erp.academic_aditya.dto.PaymentHistoryResponse;
import com.erp.academic_aditya.dto.PaymentRequest;
import com.erp.academic_aditya.entity.Bills;
import com.erp.academic_aditya.entity.Student;
import com.erp.academic_aditya.entity.StudentPayment;
import com.erp.academic_aditya.repo.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentPaymentService {

    @Autowired
    private HttpServletRequest request;

    private final StudentPaymentRepository studentPaymentRepository;
    private final StudentRepository studentRepository;
    private final BillsRepository billsRepository;


    public void processPayment(PaymentRequest paymentRequest) {
        Long studentId = paymentRequest.studentId();
        Long billId = paymentRequest.billId();
        Double dueAmount = paymentRequest.totalDue();
        Double customAmount = paymentRequest.amount();
        Boolean isTotalSelected = paymentRequest.useTotal();

        // Fetch the student and bill
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
        Bills bill = billsRepository.findById(billId)
                .orElseThrow(() -> new EntityNotFoundException("Bill not found with ID: " + billId));

        // Save the payment record
        StudentPayment payment = new StudentPayment();
        payment.setStudent(student);
        payment.setBillId(bill);
        payment.setDescription("Payment for " + bill.getDescription());

        // Handle payment logic
        if (isTotalSelected) {
            payment.setAmount(dueAmount);
            dueAmount = 0.0; // Mark bill as fully paid
        } else {
            payment.setAmount(customAmount);

            // Update remaining due amount
            if (customAmount >= dueAmount) {
                dueAmount = 0.0; // Mark bill as fully paid
            } else {
                dueAmount -= customAmount;
            }
        }

        // Save the payment
        payment.setPaymentDate(LocalDate.now());
        studentPaymentRepository.save(payment);


        billsRepository.save(bill);
    }



    public List<PaymentHistoryResponse> getPaymentHistoryForStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
        List<StudentPayment> payments = studentPaymentRepository.findByStudent_StudentId(studentId);
        return payments.stream()
                .map(payment -> new PaymentHistoryResponse(
                        payment.getId(),
                        payment.getAmount(),
                        payment.getDescription(),
                        payment.getPaymentDate()
                ))
                .collect(Collectors.toList());
    }
}
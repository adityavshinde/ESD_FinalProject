package com.erp.academic_aditya.service;

import com.erp.academic_aditya.dto.PaymentHistoryResponse;
import com.erp.academic_aditya.dto.StudentPaymentRequest;
import com.erp.academic_aditya.entity.Bills;
import com.erp.academic_aditya.entity.CreditBalance;
import com.erp.academic_aditya.entity.Student;
import com.erp.academic_aditya.entity.StudentPayment;
import com.erp.academic_aditya.exception.UnauthorisedAccessException;
import com.erp.academic_aditya.repo.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
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
    private final CreditBalanceRepository creditBalanceRepository;
    private final StudentBillsRepository studentBillsRepository;
    private final StudentRepository studentRepository;
    private final BillsRepository billsRepository;


    public void processPayment(StudentPaymentRequest paymentRequest) {
        if (paymentRequest.studentId() == null) {
            throw new IllegalArgumentException("Student ID must not be null.");
        }
        if (paymentRequest.billId() == null) {
            throw new IllegalArgumentException("Bill ID must not be null.");
        }
        if (paymentRequest.amount() == null || paymentRequest.amount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
        Long studentId = paymentRequest.studentId();
        Long billId = paymentRequest.billId();
        Double dueAmount = paymentRequest.totalDue();
        Double customAmount = paymentRequest.amount();
        Boolean isCreditSelected = paymentRequest.useCredit();
        Boolean isTotalSelected = paymentRequest.useTotal();
//        FOR AUTHORIZATION
//        String AuthenticatedId = (String)request.getAttribute("AuthenticatedIdentifier");
//        if(!AuthenticatedId.equals(String.valueOf(studentId))){
//            throw new UnauthorisedAccessException("You can only process your payments.");
//        }

        // Fetch the student and bill
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
        Bills bill = billsRepository.findById(billId)
                .orElseThrow(() -> new EntityNotFoundException("Bill not found with ID: " + billId));

        // Fetch or create a new credit balance
        CreditBalance creditBalance = creditBalanceRepository.findByStudent_StudentId(studentId)
                .orElseGet(() -> {
                    CreditBalance newBalance = new CreditBalance(student, 0.0);
                    creditBalanceRepository.save(newBalance);
                    return newBalance;
                });
        Double creditAmount = creditBalance.getBalance();


        // Save the payment record
        StudentPayment payment = new StudentPayment();
        payment.setStudent(student);
        payment.setBillId(bill);
        payment.setDescription("Payment for " + bill.getDescription());
        if(isCreditSelected) {
            payment.setAmount(paymentRequest.amount()+creditAmount);
        }else{
            payment.setAmount(paymentRequest.amount());
        }
        payment.setPaymentDate(LocalDate.now());
        studentPaymentRepository.save(payment);


        //updating the credit balance as per all the conditions
        if(isTotalSelected) {
            if(isCreditSelected){
                if(creditAmount>dueAmount){
                    creditAmount = creditAmount - dueAmount;
                    dueAmount = 0.0;
                }else{
                    dueAmount = dueAmount - creditAmount;
                    creditAmount = 0.0;
                }
            }
        }else{
            if(isCreditSelected){
                if(creditAmount>dueAmount){
                    creditAmount = creditAmount - dueAmount;
                    dueAmount = 0.0;
                    creditAmount = creditAmount + customAmount;
                }else{
                    dueAmount = dueAmount - creditAmount;
                    creditAmount = 0.0;
                    if(dueAmount<customAmount){
                        creditAmount = customAmount - dueAmount;
                    }
                }
            }else{
                if(dueAmount<customAmount){
                    customAmount = customAmount - dueAmount;
                    dueAmount = 0.0;
                    creditAmount = customAmount;
                }else{
                    dueAmount = dueAmount - customAmount;
                    customAmount = 0.0;
                }
            }
        }

        creditBalance.setBalance(creditAmount);
        creditBalanceRepository.save(creditBalance);

    }


    public List<PaymentHistoryResponse> getPaymentHistoryForStudent(Long studentId) {
//        FOR AUTHORIZATION
//        String AuthenticatedId = (String)request.getAttribute("AuthenticatedIdentifier");
//        if(!AuthenticatedId.equals(String.valueOf(studentId))){
//            throw new UnauthorisedAccessException("You can only process your payments.");
//        }

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
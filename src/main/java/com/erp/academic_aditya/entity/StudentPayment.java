package com.erp.academic_aditya.entity;

import com.erp.academic_aditya.entity.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "student_payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    private String description;

    private Double amount;

    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private Bills bill;
}

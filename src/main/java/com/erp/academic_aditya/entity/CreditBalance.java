package com.erp.academic_aditya.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "credit_balance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student; // Reference to the Student entity

    @Column(name = "balance",nullable = false)
    private Double balance = 0.00; // Credit balance with a default value


    // Constructor to create a new credit balance entry
    public CreditBalance(Student student, Double balance) {
        this.student = student;
        this.balance = balance;
    }
}
package com.erp.academic_aditya.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "student_bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class StudentBills {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @OneToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private Bills bill;
}

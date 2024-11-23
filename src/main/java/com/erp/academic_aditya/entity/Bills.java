package com.erp.academic_aditya.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bills {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private Double amount;

    @Column(name = "bill_date")
    private LocalDate billDate;

    @Column(name = "deadline")
    private LocalDate deadline;
}

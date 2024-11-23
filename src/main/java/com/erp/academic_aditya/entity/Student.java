package com.erp.academic_aditya.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;

    @Column(unique = true, nullable = false)
    private String rollNo;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    private String photographPath;

    private Double cgpa;

    private Integer totalCredits;

    private Integer graduationYear;

    @Column(name = "domain")
    private String domain; // Foreign key reference

    @Column(name = "specialization")
    private String specialization; // Foreign key reference

    @Column(name = "placement_id")
    private Long placementId; // Foreign key reference


}

package com.erp.academic_aditya.repo;

import com.erp.academic_aditya.entity.CreditBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditBalanceRepository extends JpaRepository<CreditBalance, Long> {
    Optional<CreditBalance> findByStudent_StudentId(Long studentId);
}
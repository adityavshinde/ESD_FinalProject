package com.erp.academic_aditya.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.erp.academic_aditya.entity.StudentPayment;
import java.util.List;

@Repository
public interface StudentPaymentRepository extends JpaRepository<StudentPayment, Long> {
    List<StudentPayment> findByStudent_StudentId(Long studentId);
}

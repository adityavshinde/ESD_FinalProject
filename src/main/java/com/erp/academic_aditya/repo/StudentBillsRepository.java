package com.erp.academic_aditya.repo;

import com.erp.academic_aditya.entity.StudentBills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentBillsRepository extends JpaRepository<StudentBills, Long> {

    List<StudentBills> findByStudent_StudentId(Long studentId);

    Optional<StudentBills> findByBillId(Long billId);

}

package com.erp.academic_aditya.repo;

import com.erp.academic_aditya.entity.Bills;
import com.erp.academic_aditya.entity.StudentBills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentBillsRepository extends JpaRepository<StudentBills, Long> {

//    List<StudentBills> findByBill_StudentId(Long studentId);
@Query("SELECT sb.bill FROM StudentBills sb WHERE sb.student.studentId = :studentId")
List<Bills> findBillsByStudentId(Long studentId);

    Optional<StudentBills> findByBillId(Long billId);

}

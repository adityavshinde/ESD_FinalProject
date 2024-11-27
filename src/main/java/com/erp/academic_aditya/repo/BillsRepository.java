package com.erp.academic_aditya.repo;

import com.erp.academic_aditya.entity.Bills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillsRepository extends JpaRepository<Bills, Long> {
    @Query("SELECT b FROM Bills b WHERE b.id = :id")
    Optional<Bills> findById(@Param("id") Long id);


}

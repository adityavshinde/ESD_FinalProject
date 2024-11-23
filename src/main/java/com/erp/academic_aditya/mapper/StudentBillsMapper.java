package com.erp.academic_aditya.mapper;

import com.erp.academic_aditya.entity.Bills;
import com.erp.academic_aditya.entity.Student;
import com.erp.academic_aditya.entity.StudentBills;
import org.springframework.stereotype.Service;

@Service
public class StudentBillsMapper {

    public StudentBills toEntity( Student student, Bills bill) {
        return StudentBills.builder()
                .student(student)
                .bill(bill)
                .build();
    }

}

package com.erp.academic_aditya.mapper;

import com.erp.academic_aditya.dto.StudentRequest;
import com.erp.academic_aditya.dto.StudentResponse;
import com.erp.academic_aditya.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toEntity(StudentRequest request) {
        return Student.builder()
                .rollNo(request.rollNo())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .photographPath(request.photographPath())
                .cgpa(request.cgpa())
                .totalCredits(request.totalCredits())
                .graduationYear(request.graduationYear())
                .domain(request.domain())
                .specialization(request.specialization())
                .placementId(request.placementId())
                .build();
    }

    public StudentResponse toStudentResponse(Student student) {
        return new StudentResponse(
                student.getStudentId(),
                student.getRollNo(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhotographPath(),
                student.getCgpa(),
                student.getTotalCredits(),
                student.getGraduationYear(),
                student.getDomain(),
                student.getSpecialization(),
                student.getPlacementId()
        );
    }
}

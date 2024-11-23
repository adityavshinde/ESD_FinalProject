package com.erp.academic_aditya.service;

import com.erp.academic_aditya.dto.StudentRequest;
import com.erp.academic_aditya.dto.StudentResponse;
import com.erp.academic_aditya.entity.Student;
import com.erp.academic_aditya.exception.StudentNotFound;
import com.erp.academic_aditya.helper.EncryptionService;
import com.erp.academic_aditya.helper.JwtHelper;
import com.erp.academic_aditya.mapper.StudentMapper;
import com.erp.academic_aditya.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepo;
    private final StudentMapper studentMapper;
    private final EncryptionService encryptionService;
    private final JwtHelper jwtHelper;



    // Helper: Validate JWT token
    private boolean isValidToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) return false;

        String jwtToken = token.substring(7); // Extract the token part
        return jwtHelper.validateToken(jwtToken);
    }

    public String saveStudent(StudentRequest studentRequest) {

        Student student = studentMapper.toEntity(studentRequest);
        student.setPassword(encryptionService.encode(student.getPassword()));
        studentRepo.save(student);
        return "Student created successfully!";
    }


    public String getStudentById(Long id, String token) {
        if (!isValidToken(token)) return "Invalid Token";
        Student student = studentRepo.findById(id).orElseThrow(() -> new StudentNotFound("Student not found"));
        return student.toString(); // Or map it to a response object if needed
    }

    public String updateStudent(Long id, StudentRequest studentRequest, String token) {
        if (!isValidToken(token)) return "Invalid Token";
        Student existingStudent = studentRepo.findById(id).orElseThrow(() -> new StudentNotFound("Student not found"));
        // Update fields
        existingStudent.setFirstName(studentRequest.firstName());
        existingStudent.setEmail(studentRequest.email());
        existingStudent.setPassword(encryptionService.encode(studentRequest.password()));
        studentRepo.save(existingStudent);
        return "Student updated successfully!";
    }

    public String deleteStudent(Long id, String token) {
        if (!isValidToken(token)) return "Invalid Token";
        studentRepo.deleteById(id);
        return "Student deleted successfully!";
    }

}

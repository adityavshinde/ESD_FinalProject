package com.erp.academic_aditya.dto;

public record LoginResponse(
        Long id,
        String firstName,
        String identifier, // Either email for admin or roll number for student
        String message,
        String token // The JWT or session token
) {}
package com.erp.academic_aditya.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentRequest(

        @JsonProperty("roll_no")
        String rollNo,

        @NotBlank
        @JsonProperty("first_name")
        String firstName,

        @NotBlank @JsonProperty("last_name")
        String lastName,

        @Email
        @NotBlank
        @JsonProperty("email")
        String email,

        @NotBlank
        @JsonProperty("password")
        String password,

        @JsonProperty("photograph_path")
        String photographPath,

        @JsonProperty("cgpa")
        Double cgpa,

        @JsonProperty("total_credits")
        Integer totalCredits,

        @JsonProperty("graduation_year")
        Integer graduationYear,

        @JsonProperty("domain")
        String domain, // Foreign key

        @JsonProperty("specialization")
        String specialization, // Foreign key

        @JsonProperty("placement_id")
        Long placementId // Foreign key
) {}

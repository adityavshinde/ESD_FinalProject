package com.erp.academic_aditya.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;

public record StudentResponse(
        @JsonProperty("id")
        Long id,
        @JsonProperty("roll_no")
        String rollNo,
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        @JsonProperty("email")
        String email,
        @JsonProperty("photograph_path")
        String photographPath,
        @JsonProperty("cgpa")
        Double cgpa,
        @JsonProperty("total_credits")
        Integer totalCredits,
        @JsonProperty("graduation_year")
        Integer graduationYear,
        @JsonProperty("domain")
        String domain,
        @JsonProperty("specialization")
        String specialization,
        @JsonProperty("placement_id")
        Long placementId
) {}

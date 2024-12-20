package com.erp.academic_aditya.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StudentPaymentRequest(

        @NotNull @JsonProperty("student_id")
        Long studentId,
        @NotNull @JsonProperty("bill_id")
        Long billId,
        @NotNull @JsonProperty("amount")
        Double amount ,    // Amount paid
        @JsonProperty("description")
        String description,
        @JsonProperty("total")
        boolean useTotal
) {
}

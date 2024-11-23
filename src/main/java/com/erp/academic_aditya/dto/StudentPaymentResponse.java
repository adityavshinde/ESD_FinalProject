package com.erp.academic_aditya.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public record StudentPaymentResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("student_name") String studentName,
        @JsonProperty("description") String description,
        @JsonProperty("amount") Double amount,
        @JsonProperty("payment_date") LocalDate paymentDate,
        @JsonProperty("bill_description") String billDescription
) {}

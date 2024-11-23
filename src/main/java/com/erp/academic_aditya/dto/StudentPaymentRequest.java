package com.erp.academic_aditya.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record StudentPaymentRequest(
        @NotNull @JsonProperty("student_id") Long studentId,
        @NotNull @JsonProperty("description") String description,
        @NotNull @JsonProperty("amount") Double amount,
        @NotNull @JsonProperty("payment_date") LocalDate paymentDate,
        @NotNull @JsonProperty("bill_id") Long billId
) {}

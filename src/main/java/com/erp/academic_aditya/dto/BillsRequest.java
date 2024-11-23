package com.erp.academic_aditya.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record BillsRequest(
        @NotBlank @JsonProperty("description")
        String description,
        @NotNull @JsonProperty("amount")
        Double amount,
        @NotNull @JsonProperty("bill_date")
        LocalDate billDate,
        @NotNull @JsonProperty("deadline")
        LocalDate deadline,

        Long studentId
) {}

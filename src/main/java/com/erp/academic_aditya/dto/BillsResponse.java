package com.erp.academic_aditya.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public record BillsResponse(
        Long id,
        String message
//        @JsonProperty("description") String description,
//        @JsonProperty("amount") Double amount,
//        @JsonProperty("bill_date") LocalDate billDate,
//        @JsonProperty("deadline") LocalDate deadline
) {}

package com.erp.academic_aditya.dto;

import java.time.LocalDate;

public record AllBillResponse(
        Long billId,
        String description,
        Double amount,
        LocalDate dueDate,
        LocalDate deadline,
        Double currentDue
) {
}
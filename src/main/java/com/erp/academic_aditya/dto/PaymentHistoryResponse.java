package com.erp.academic_aditya.dto;

import java.time.LocalDate;

public record PaymentHistoryResponse(
        Long paymentId,
        Double amount,
        String Description,
        LocalDate paymentDate
) {}
package com.erp.academic_aditya.mapper;

import com.erp.academic_aditya.dto.AllBillResponse;
import com.erp.academic_aditya.dto.BillsRequest;
import com.erp.academic_aditya.entity.Bills;
import org.springframework.stereotype.Service;

@Service
public class BillsMapper {

    public Bills toEntity(BillsRequest request) {
        return Bills.builder()
                .description(request.description())
                .amount(request.amount())
                .billDate(request.billDate())
                .deadline(request.deadline())
                .build();
    }

    public AllBillResponse toAllBillResponse(Bills bill, Double currentDue) {
        return new AllBillResponse(
                bill.getId(),
                bill.getDescription(),
                bill.getAmount(),
                bill.getBillDate(),
                bill.getDeadline(),
                currentDue
        );
    }
}

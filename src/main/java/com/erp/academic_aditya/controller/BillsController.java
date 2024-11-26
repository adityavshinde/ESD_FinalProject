package com.erp.academic_aditya.controller;

import com.erp.academic_aditya.dto.BillsRequest;
import com.erp.academic_aditya.dto.BillsResponse;
import com.erp.academic_aditya.entity.Bills;
import com.erp.academic_aditya.service.BillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bills")
public class BillsController {

    @Autowired
    private BillsService billsService;

    // Fetch a bill by ID
    @GetMapping("/{id}")
    public Bills getBillById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        return billsService.getBillById(id, token);
    }

    // Add a new bill
    @PostMapping
    public BillsResponse addBill(@RequestBody BillsRequest billsRequest) {
        return billsService.addBill(billsRequest);
    }

}

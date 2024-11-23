package com.erp.academic_aditya.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class StudentNotFound extends RuntimeException {

    private final String msg;
}
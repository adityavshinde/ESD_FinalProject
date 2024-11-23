package com.erp.academic_aditya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AcademicAdityaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcademicAdityaApplication.class, args);
    }

}

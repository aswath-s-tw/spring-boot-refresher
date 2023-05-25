package com.allstate_onboarding.spring_boot_refresher.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CreateEmployeeResponse {
    private Long id;

    private String name;

    private String aadhar;

    private String department;

    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "UTC")
    private Date dateOfBirth;

    private Integer age;
}

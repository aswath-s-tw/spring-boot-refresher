package com.allstate_onboarding.spring_boot_refresher.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateEmployeeDepartmentByAadharRequest {


    @Pattern(regexp = "\\d{5}", message = "'aadhar' number should be exactly 5 digits")
    private String aadhar;

    @NotBlank(message = "'department' is required.")
    private String department;
}

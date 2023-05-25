package com.allstate_onboarding.spring_boot_refresher.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetEmployeeByAadharRequest {

    @Pattern(regexp = "\\d{5}", message = "'aadhar' number should be exactly 5 digits")
    private String aadhar;
}

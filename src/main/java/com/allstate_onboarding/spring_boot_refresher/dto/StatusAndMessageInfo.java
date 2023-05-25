package com.allstate_onboarding.spring_boot_refresher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusAndMessageInfo {
    private String status;
    private String message;
}

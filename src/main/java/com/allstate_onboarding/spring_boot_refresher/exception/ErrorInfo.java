package com.allstate_onboarding.spring_boot_refresher.exception;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorInfo {
    private List<String> errors;
    private Integer errorCode;
    private LocalDateTime timestamp;
}

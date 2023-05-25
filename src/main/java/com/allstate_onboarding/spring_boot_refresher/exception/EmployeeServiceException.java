package com.allstate_onboarding.spring_boot_refresher.exception;

public class EmployeeServiceException extends RuntimeException {
    public EmployeeServiceException(String message) {
        super(message);
    }
}

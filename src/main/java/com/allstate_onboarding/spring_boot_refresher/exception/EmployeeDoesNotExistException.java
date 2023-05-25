package com.allstate_onboarding.spring_boot_refresher.exception;

public class EmployeeDoesNotExistException extends RuntimeException {
    public EmployeeDoesNotExistException(String message) {
        super(message);
    }
}

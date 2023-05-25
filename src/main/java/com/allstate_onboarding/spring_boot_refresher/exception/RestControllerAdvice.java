package com.allstate_onboarding.spring_boot_refresher.exception;

import com.allstate_onboarding.spring_boot_refresher.dto.StatusAndMessageInfo;
import org.modelmapper.MappingException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorInfo> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return buildResponseEntityWithErrorInfo(exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
    }

    private static ResponseEntity<ErrorInfo> buildResponseEntityWithErrorInfo(List<String> exceptions) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());

        errorInfo.setErrors(exceptions);
        errorInfo.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ErrorInfo> handleRunTimeException(RuntimeException exception) {
        return buildResponseEntityWithErrorInfo(
                List.of(exception.getMessage())
        );
    }

    @ExceptionHandler(value = {MappingException.class})
    public ResponseEntity<ErrorInfo> handleModelMapperException(MappingException exception) {
        List<String> errors = exception.getErrorMessages().stream().map(ErrorMessage::getMessage).toList();
        return buildResponseEntityWithErrorInfo(
                errors
        );
    }


    @ExceptionHandler(value = {EmployeeDoesNotExistException.class})
    public ResponseEntity<StatusAndMessageInfo> handleEmployeeDoesNotExistException(EmployeeDoesNotExistException exception) {
        return new ResponseEntity<>(
                new StatusAndMessageInfo("Failed", "Employee does not exist"),
                HttpStatus.NOT_FOUND
        );
    }

}
package com.allstate_onboarding.spring_boot_refresher.controller;


import com.allstate_onboarding.spring_boot_refresher.dto.CreateEmployeeRequest;
import com.allstate_onboarding.spring_boot_refresher.dto.CreateEmployeeResponse;
import com.allstate_onboarding.spring_boot_refresher.dto.GetEmployeeByAadharRequest;
import com.allstate_onboarding.spring_boot_refresher.dto.GetEmployeeResponse;
import com.allstate_onboarding.spring_boot_refresher.dto.StatusAndMessageInfo;
import com.allstate_onboarding.spring_boot_refresher.dto.UpdateEmployeeDepartmentByAadharRequest;
import com.allstate_onboarding.spring_boot_refresher.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    public ResponseEntity<CreateEmployeeResponse> createEmployee(
            @Valid @RequestBody CreateEmployeeRequest createEmployeeRequestDTO
    ) {
        CreateEmployeeResponse employeeResponse = employeeService.create(createEmployeeRequestDTO);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @GetMapping("/by_aadhar")
    public ResponseEntity<GetEmployeeResponse> getEmployeeByAadhar(
            @Valid @RequestBody GetEmployeeByAadharRequest getEmployeeByAadharRequest
    ) {
        GetEmployeeResponse response = employeeService.getEmployeeByAadhar(getEmployeeByAadharRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StatusAndMessageInfo> updateEmployee(
            @Valid @RequestBody UpdateEmployeeDepartmentByAadharRequest updateEmployeeDepartmentByAadharRequest
    ) {
        employeeService.updateEmployee(updateEmployeeDepartmentByAadharRequest);
        return new ResponseEntity<>(new StatusAndMessageInfo(
                "Success",
                "Updated successfully"
        ), HttpStatus.OK);
    }

}

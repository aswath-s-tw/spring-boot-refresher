package com.allstate_onboarding.spring_boot_refresher.service;

import com.allstate_onboarding.spring_boot_refresher.dto.CreateEmployeeRequest;
import com.allstate_onboarding.spring_boot_refresher.dto.CreateEmployeeResponse;
import com.allstate_onboarding.spring_boot_refresher.dto.GetEmployeeByAadharRequest;
import com.allstate_onboarding.spring_boot_refresher.dto.GetEmployeeResponse;
import com.allstate_onboarding.spring_boot_refresher.dto.UpdateEmployeeDepartmentByAadharRequest;
import com.allstate_onboarding.spring_boot_refresher.entity.Employee;
import com.allstate_onboarding.spring_boot_refresher.exception.EmployeeDoesNotExistException;
import com.allstate_onboarding.spring_boot_refresher.exception.EmployeeServiceException;
import com.allstate_onboarding.spring_boot_refresher.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class EmployeeService {

    private final ModelMapper modelMapper;
    private final EmployeeRepository repository;

    private static final String EMPLOYEE_NOT_FOUND_MESSAGE = "Employee does not exist.";

    @Autowired
    public EmployeeService(ModelMapper modelMapper, EmployeeRepository repository) {
        this.repository = repository;
        this.modelMapper = modelMapper;

        modelMapper.typeMap(CreateEmployeeRequest.class, Employee.class)
                .addMappings(
                        mapper -> mapper.skip(
                                CreateEmployeeRequest::getDateOfBirth,
                                Employee::setDateOfBirth
                        )
                );
    }


    public CreateEmployeeResponse create(CreateEmployeeRequest createEmployeeRequestDTO) {
        Date dob = createEmployeeRequestDTO.parsedDateOfBirth();
        Employee employee = modelMapper.map(createEmployeeRequestDTO, Employee.class);
        Optional<Employee> employeeInDb = repository.findByAadhar(createEmployeeRequestDTO.getAadhar());
        if (employeeInDb.isPresent()) {
            throw new EmployeeServiceException("Employee already exists.");
        }
        employee.setDateOfBirth(dob);
        employee.setAge(Date.from(Instant.now()).getYear() - dob.getYear());
        repository.save(employee);
        return modelMapper.map(employee, CreateEmployeeResponse.class);
    }

    public GetEmployeeResponse getEmployeeByAadhar(GetEmployeeByAadharRequest getEmployeeByAadharRequestDTO) {
        Optional<Employee> employeeInDb = repository.findByAadhar(getEmployeeByAadharRequestDTO.getAadhar());
        Employee employee = employeeInDb.orElseThrow(() -> new EmployeeDoesNotExistException(EMPLOYEE_NOT_FOUND_MESSAGE));
        return modelMapper.map(employee, GetEmployeeResponse.class);
    }

    public void updateEmployee(UpdateEmployeeDepartmentByAadharRequest updateEmployeeDepartmentByAadharRequest) {
        Optional<Employee> employeeInDb = repository.findByAadhar(updateEmployeeDepartmentByAadharRequest.getAadhar());
        Employee employee = employeeInDb.orElseThrow(() -> new EmployeeDoesNotExistException(EMPLOYEE_NOT_FOUND_MESSAGE));
        employee.setDepartment(updateEmployeeDepartmentByAadharRequest.getDepartment());
        repository.save(employee);
    }
}

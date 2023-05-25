package com.allstate_onboarding.spring_boot_refresher.repository;

import com.allstate_onboarding.spring_boot_refresher.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByAadhar(String aadhar);
}

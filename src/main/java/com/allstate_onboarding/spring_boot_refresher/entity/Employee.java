package com.allstate_onboarding.spring_boot_refresher.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "aadhar", unique = true)
    private String aadhar;

    @Column(name = "department")
    private String department;

    @Column(name = "age")
    private Integer age;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;
}

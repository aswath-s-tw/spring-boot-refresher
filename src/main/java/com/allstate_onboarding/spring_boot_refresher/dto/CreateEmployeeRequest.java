package com.allstate_onboarding.spring_boot_refresher.dto;


import com.allstate_onboarding.spring_boot_refresher.exception.EmployeeServiceException;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.spi.ErrorMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Data
public class CreateEmployeeRequest {

    @NotBlank(message = "'name' is required")
    @Pattern(regexp = "^[A-Za-z]*$", message = "'name' may contain only alphabets")
    private String name;

    @Pattern(regexp = "\\d{5}", message = "'aadhar' number should be exactly 5 digits")
    private String aadhar;

    @NotBlank(message = "'department' is required")
    private String department;

    @NotBlank(message = "'date_of_birth' is required")
    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    public Date parsedDateOfBirth() {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        dateFormatter.setLenient(false);
        Date dob;
        try {
            dob = dateFormatter.parse(dateOfBirth);
        } catch (ParseException e) {
            throw new EmployeeServiceException("Date is not in format 'dd/MM/yyyy' or is not valid");
        }
        if (dob.after(Date.from(Instant.now()))) {
            throw new EmployeeServiceException("Date should be past or present");
        }
        return dob;
    }
}

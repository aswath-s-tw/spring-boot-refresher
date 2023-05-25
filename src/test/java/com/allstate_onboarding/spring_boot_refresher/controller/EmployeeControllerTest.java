package com.allstate_onboarding.spring_boot_refresher.controller;

import com.allstate_onboarding.spring_boot_refresher.exception.RestControllerAdvice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class EmployeeControllerTest {

    @Autowired
    EmployeeController employeeController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .setControllerAdvice(new RestControllerAdvice())
                .build();
    }


    @Test
    void should_return_200_when_options_request_made_to_create_employee() throws Exception {
        mockMvc.perform(
                        options("/employees")
                ).andExpect(
                        status().is2xxSuccessful()
                )
                .andReturn();
    }

    @Test
    void should_return_400_when_create_employee_called_with_empty_body() throws Exception {
        mockMvc.perform(
                        post("/employees")
                ).andExpect(
                        status().isBadRequest()
                ).andDo(print())
                .andReturn();
    }
}

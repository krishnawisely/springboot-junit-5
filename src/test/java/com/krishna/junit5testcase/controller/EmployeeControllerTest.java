package com.krishna.junit5testcase.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krishna.junit5testcase.dto.EmployeeDTO;
import com.krishna.junit5testcase.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
   
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @BeforeEach
    public void init(){
        // objectMapper = new ObjectMapper();
        // mockMvc = MockMvcBuilders.standaloneSetup(new EmployeeController()).build();
    }

    @Test
    public void saveEmployees() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setAge(26);
        employeeDTO.setName("krishna");

        Mockito.doNothing().when(employeeService).saveEmployee(employeeDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/employees").content(objectMapper.writeValueAsString(employeeDTO))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

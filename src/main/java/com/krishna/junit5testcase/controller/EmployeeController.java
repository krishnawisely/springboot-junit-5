package com.krishna.junit5testcase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krishna.junit5testcase.dto.EmployeeDTO;
import com.krishna.junit5testcase.service.EmployeeService;

import jakarta.validation.Valid;
@RequestMapping("employees")
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws Exception{
        employeeService.saveEmployee(employeeDTO);
        return ResponseEntity.ok("Employee saved successfully");
    }
}

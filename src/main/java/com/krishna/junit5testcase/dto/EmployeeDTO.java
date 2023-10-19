package com.krishna.junit5testcase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    @NotBlank(message = "Employee name should be mandatory")
    private String name;
    @NotNull(message = "Age should be mandatory")
    private Integer age;
}

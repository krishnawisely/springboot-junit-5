package com.krishna.junit5testcase.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishna.junit5testcase.dto.EmployeeDTO;
import com.krishna.junit5testcase.entity.Employee;
import com.krishna.junit5testcase.exception.BusinessException;
import com.krishna.junit5testcase.repository.EmployeeRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public void saveEmployee(EmployeeDTO employeeDTO) throws BusinessException{
        try {
            // Here save logic for employee
            log.info("Employee name: {}",employeeDTO.getName());
            ModelMapper modelMapper = new ModelMapper();
            Employee employee = modelMapper.map(employeeDTO, Employee.class);
            employeeRepository.save(employee);
            // Test exception handler
            // throw new Exception("Some error");
        } catch(Exception e){
            log.error(e.getMessage(),e);
            throw new BusinessException("Employee save failed!", e);
        }
    }
}

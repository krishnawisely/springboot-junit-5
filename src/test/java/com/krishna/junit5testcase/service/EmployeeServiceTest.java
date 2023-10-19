package com.krishna.junit5testcase.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.krishna.junit5testcase.dto.EmployeeDTO;
import com.krishna.junit5testcase.entity.Employee;
import com.krishna.junit5testcase.exception.BusinessException;
import com.krishna.junit5testcase.repository.EmployeeRepository;
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void saveEmployee_success() throws Exception{
        ArgumentCaptor<Employee> argumentCaptor = ArgumentCaptor.forClass(Employee.class);
        EmployeeDTO employeeDTO = getEmployeeDTODetail();
        Mockito.when(employeeRepository.save(argumentCaptor.capture())).thenReturn(getEmployeeDetail());
        employeeService.saveEmployee(employeeDTO);
        assertEquals(employeeDTO.getName(), argumentCaptor.getValue().getName());
    }

    @Test
    public void saveEmployee_failure() throws Exception{
        EmployeeDTO employeeDTO = getEmployeeDTODetail();
        Mockito.when(employeeRepository.save(Mockito.any())).thenThrow(DataIntegrityViolationException.class);
        // employeeService.saveEmployee(employeeDTO);
        BusinessException bs = assertThrows(BusinessException.class, () -> employeeService.saveEmployee(employeeDTO));
        assertTrue(bs.getMessage().contains("Employee save failed!"));
    }

    private EmployeeDTO getEmployeeDTODetail(){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setAge(26);
        employeeDTO.setName("krishna");
        return employeeDTO;
    }

    private Employee getEmployeeDetail(){
        Employee employee= new Employee();
        employee.setAge(26);
        employee.setName("krishna");
        return employee;
    }
}

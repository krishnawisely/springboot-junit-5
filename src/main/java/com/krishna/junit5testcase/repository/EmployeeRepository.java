package com.krishna.junit5testcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krishna.junit5testcase.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
    
}

package com.shifts.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shifts.backend.model.Calendar;
import com.shifts.backend.model.Employee;

import jakarta.annotation.PostConstruct;

@Repository
public class EmployeeRepoDemo {
     private final List<Employee> employeeList = new ArrayList<>();
        public EmployeeRepoDemo(){
    
        }
        public List<Employee> findAll(){
            return employeeList;
        }

        public void save( Employee employee){
            employeeList.add(employee);
        }
}

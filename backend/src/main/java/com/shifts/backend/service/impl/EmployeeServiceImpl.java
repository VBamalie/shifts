package com.shifts.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifts.backend.model.Employee;
import com.shifts.backend.repository.EmployeeRepo;
import com.shifts.backend.service.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Employee saveEmployee(Employee employee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveEmployee'");
    }

    @Override
    public List<Employee> getAllEmployees() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllEmployees'");
    }

    @Override
    public Employee getEmployeeById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEmployeeById'");
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEmployee'");
    }

    @Override
    public void deleteEmployee(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEmployee'");
    }

    @Override
    public Employee getAllEmployeesByCalendarId(int calendarId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllEmployeesByCalendarId'");
    }

}

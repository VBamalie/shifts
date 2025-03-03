package com.shifts.backend.service.service;

import java.util.List;

import com.shifts.backend.model.Employee;

//this is the interface used for employee service impl
public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    Employee getAllEmployeesByCalendarId(int calendarId);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(int id);
}

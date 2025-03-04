package com.shifts.backend.service.service;

import java.util.List;

import com.shifts.backend.model.Employee;

//this is the interface used for employee service impl
public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee getAllEmployeesByCalendarId(Long calendarId);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Long id);
    boolean isAvailable(Long id);
    int hoursWorkedThisWeek(Long id);
    int hoursWorkdToday(Long id);

}

package com.shifts.backend.service.service;

import java.util.List;

import com.shifts.backend.model.Employee;
import com.shifts.backend.model.Shift;
import com.shifts.backend.model.TimeOffRequest;

//this is the interface used for employee service impl
public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployeesByCalendarId(Long calendarId);
    Employee updateEmployee(Employee employee, Long id);
    String deleteEmployee(Long id);
    Employee addShift(Long id, Shift shift);
    Employee removeShift(Long id, Shift shift);
    Employee addTimeOffRequest(Long id, TimeOffRequest timeOffRequest);
    Employee removeTimeOffRequest(Long id, TimeOffRequest timeOffRequest);
    boolean isAvailable(Long id);
    int hoursWorkedThisWeek(Long id);
    int hoursWorkdToday(Long id);

}

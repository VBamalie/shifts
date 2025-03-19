package com.shifts.backend.service.service;

import java.util.List;

import com.shifts.backend.model.Employee;
import com.shifts.backend.model.Shift;

//this is the interface used for employee service impl
public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployeesByCalendarId(Long calendarId);
    Employee updateEmployee(Employee employee, Long id);
    String deleteEmployee(Long id);
    Shift addShift(Long id, Long shiftId);
    Shift removeShift(Long id, Long shiftId);
    boolean isAvailable(Long id);
    int hoursWorkedThisWeek(Long id);
    int hoursWorkdToday(Long id);

}

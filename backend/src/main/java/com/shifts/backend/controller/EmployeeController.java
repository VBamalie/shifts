package com.shifts.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.backend.model.Employee;
import com.shifts.backend.service.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    
    @PostMapping("/")
    public Employee saveEmployee(Employee employee) {
        //TODO: process POST request
        return employee;
        }
    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(Long id) {
        return employeeService.getEmployeeById(id);
    }
    @GetMapping("/calendar/{id}")
    public Employee getAllEmployeesByCalendarId(Long calendarId) {
        return employeeService.getAllEmployeesByCalendarId(calendarId);
    }
    @PostMapping("/update")
    public Employee updateEmployee(Employee employee) {
        return employeeService.updateEmployee(employee);
    }
    @PostMapping("/delete")
    public void deleteEmployee(Long id) {
        employeeService.deleteEmployee(id);
    }
    @GetMapping("/availability/{id}")
    public boolean isAvailability(Long id) {
        return employeeService.isAvailable(id);
    }
    @GetMapping("/worked/week/{id}")
    public int getWorkedHours(Long id) {
        return employeeService.hoursWorkedThisWeek(id);
    }
    @GetMapping("/worked/today/{id}")
    public int getWorkedHoursToday(Long id) {
        return employeeService.hoursWorkdToday(id);
    }

}

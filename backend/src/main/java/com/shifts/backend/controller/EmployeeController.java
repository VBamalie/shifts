package com.shifts.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.backend.model.Employee;
import com.shifts.backend.service.service.CalendarService;
import com.shifts.backend.service.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CalendarService calendarService;
    
    @PostMapping("/{id}")
    public Employee saveEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {
        employee.setCalendar(calendarService.getCalendarById(id));
        employeeService.saveEmployee(employee);
        return employee;
        }
    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id")Long id) {
        return employeeService.getEmployeeById(id);
    }
    @GetMapping("/calendar/{id}")
    public List<Employee> getAllEmployeesByCalendarId(@PathVariable("id") Long calendarId) {
        return employeeService.getAllEmployeesByCalendarId(calendarId);
    }
    @PutMapping("/{id}")
    public Employee updateEmployee(@RequestBody Employee employee , @PathVariable("id") Long id) {
        return employeeService.updateEmployee(employee, id);
    }
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }
    @GetMapping("/availability/{id}")
    public boolean isAvailability(@PathVariable("id")Long id) {
        return employeeService.isAvailable(id);
    }
    @GetMapping("/worked/week/{id}")
    public int getWorkedHours(@PathVariable("id") Long id) {
        return employeeService.hoursWorkedThisWeek(id);
    }
    @GetMapping("/worked/today/{id}")
    public int getWorkedHoursToday(@PathVariable("id") Long id) {
        return employeeService.hoursWorkdToday(id);
    }
}

package com.shifts.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.backend.model.Employee;
import com.shifts.backend.model.LoginRequest;
import com.shifts.backend.model.Shift;
import com.shifts.backend.service.service.CalendarService;
import com.shifts.backend.service.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

//Crud operations for the Employee class as well as a method to get all employees by calendar id, find out the employees's availability, and get how many hours they have worked in a day and week
@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final CalendarService calendarService;

    EmployeeController(EmployeeService employeeService, CalendarService calendarService) {
        this.employeeService = employeeService;
        this.calendarService = calendarService;
    }

    @PostMapping("/register/{id}")
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
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/calendar/{id}")
    public List<Employee> getAllEmployeesByCalendarId(@PathVariable("id") Long calendarId) {
        return employeeService.getAllEmployeesByCalendarId(calendarId);
    }

    @PutMapping("/addShift/{id}")
    public Shift addShift(@RequestBody Long shiftId, @PathVariable("id") Long id) {
        return employeeService.addShift(id, shiftId);
    }

    @PutMapping("/removeShift/{id}")
    public Shift putMethodName(@PathVariable Long id, @RequestBody Long shiftId) {
        return employeeService.removeShift(id, shiftId);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {
        return employeeService.updateEmployee(employee, id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/availability/{id}")
    public boolean isAvailability(@PathVariable("id") Long id) {
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        try {
            boolean isAuthenticated = employeeService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            if (isAuthenticated) {
                session.setAttribute("employee", loginRequest.getEmail());
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing your request.");
        }
    }
}

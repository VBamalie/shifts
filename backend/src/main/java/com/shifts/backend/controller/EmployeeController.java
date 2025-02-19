package com.shifts.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.backend.model.Employee;
import com.shifts.backend.repository.DemoRepo.EmployeeRepoDemo;

//This is the controller for the employee. It is responsible for handling all requests to the /api/employee endpoint. The front end will send a request to this endpoint to get all employees, create a new employee, or update an employee.

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeRepoDemo repository;
    public EmployeeController(EmployeeRepoDemo repository){
        this.repository = repository;
    }
    @GetMapping("")
    public List<Employee> getAllEmployees(){
            return null;
            //TODO:Write
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(int id){
            return null;
             //TODO:Write
    }
    @GetMapping("/CalendarId/{id}")
    public List<Employee>
    getEmployeesByCalendarId(int id){
            return null;
            //TODO:Write
    }

    @PostMapping("")
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public void create(@RequestBody Employee employee){
         //TODO:Write
    }
    @PutMapping("/{id}")
    public void update(@RequestBody Employee employee){
         //TODO:Write
    }
    @DeleteMapping("/{id}")
    public void delete(int id){
         //TODO:Write
    }

}

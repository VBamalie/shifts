package com.shifts.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
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

//This is the controller for the employee objects. It is responsible for handling all requests to the /api/employee endpoint. The front end will send a request to this endpoint to get all employees, create a new employee, update an employee, and delete an employee.
//TODO: add a put request to add a shift to an employee.
//TODO: add a put request to add a time off request to an employee.
//TODO: add a put request to remove a shift from an employee.
//TODO: add a put request to remove a time off request from an employee.
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeRepoDemo repository;
    public EmployeeController(EmployeeRepoDemo repository){
        this.repository = repository;
    }
    //This grabs all employees in the database. this will never be used in the front end.
    @GetMapping("")
    public List<Employee> getAllEmployees(){
            return null;
            //TODO:Write
    }
    //This grabs and employee by their id.
    @GetMapping("/{id}")
    public Employee getEmployeeById(int id){
            return null;
             //TODO:Write
    }

    //This grabs all employees with a certain calendar id.
    //TODO: consider adding pagination to this method.
    @GetMapping("/CalendarId/{id}")
    public List<Employee>
    getEmployeesByCalendarId(int id){
            return null;
            //TODO:Write
    }

    //This creates a new employee.
    @PostMapping("")
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public void create(@RequestBody Employee employee){
         //TODO:Write
    }

    //This updates an employee. 
    //TODO: Consider adding a put request to add a shift to an employee.
    @PutMapping("/{id}")
    public void update(@RequestBody Employee employee){
         //TODO:Write
    }

    //This deletes an employee.
    @DeleteMapping("/{id}")
    public void delete(int id){
         //TODO:Write
    }

}

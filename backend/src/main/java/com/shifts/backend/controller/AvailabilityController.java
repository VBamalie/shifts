package com.shifts.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.backend.model.EmployeeAvailability;
import com.shifts.backend.service.service.EmployeeAvailabilityService;
import com.shifts.backend.service.service.EmployeeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//Basic Create, Read and Update Controller for Availability Class
@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {
    private final EmployeeAvailabilityService availabilityService;

    private final EmployeeService employeeService;

    public AvailabilityController(EmployeeAvailabilityService availabilityService, EmployeeService employeeService) {
        this.availabilityService = availabilityService;
        this.employeeService = employeeService;
    }

    @PostMapping("/{id}")
    public EmployeeAvailability saveAvailability(@RequestBody EmployeeAvailability availability, @PathVariable("id") Long id) {
        availability.setEmployee(employeeService.getEmployeeById(id));
        availabilityService.saveAvailability(availability);
        return availability;
    }

    @GetMapping("/")
    public List<EmployeeAvailability> getAllAvailabilities() {
        return availabilityService.getAllAvailabilities();
    }
    @GetMapping("/{id}")
    public EmployeeAvailability getAvailabilityById(@PathVariable("id") Long id) {
        return availabilityService.getAvailabilityById(id);
    }
    @PutMapping("/{id}")
    public EmployeeAvailability updateAvailability(@RequestBody EmployeeAvailability availability, @PathVariable("id") Long id) {
        return availabilityService.updateAvailability(availability, id);
    }

    //no delete method required because the availability entity will be deleted when the employee is deleted.
}

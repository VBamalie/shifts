package com.shifts.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.backend.model.Availability;
import com.shifts.backend.service.service.AvailabilityService;
import com.shifts.backend.service.service.EmployeeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {
    @Autowired
    private AvailabilityService availabilityService;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/{id}")
    public Availability saveAvailability(@RequestBody Availability availability, @PathVariable("id") Long id) {
        availability.setEmployee(employeeService.getEmployeeById(id));
        availabilityService.saveAvailability(availability);
        return availability;
    }

    @GetMapping("/")
    public List<Availability> getAllAvailabilities() {
        return availabilityService.getAllAvailabilities();
    }
    @GetMapping("/{id}")
    public Availability getAvailabilityById(@PathVariable("id") Long id) {
        return availabilityService.getAvailabilityById(id);
    }
    @PutMapping("/{id}")
    public Availability updateAvailability(@RequestBody Availability availability, @PathVariable("id") Long id) {
        return availabilityService.updateAvailability(availability, id);
    }

    //no delete method required because the availability entity will be deleted when the employee is deleted.
}

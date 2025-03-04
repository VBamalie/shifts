package com.shifts.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.backend.model.Availability;
import com.shifts.backend.service.service.AvailabilityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {
    @Autowired
    private AvailabilityService availabilityService;

    @PostMapping("/")
    public Availability saveAvailability(@RequestBody Availability availability) {
        //TODO: process POST request
        
        return availability;
    }

    @GetMapping("/")
    public List<Availability> getAllAvailabilities() {
        return availabilityService.getAllAvailabilities();
    }
    @GetMapping("/{id}")
    public Availability getAvailabilityById(@RequestParam Long id) {
        return availabilityService.getAvailabilityById(id);
    }
    @GetMapping("/employee/{employeeId}")
    public Availability getAvailabilityByEmployeeId(@RequestParam Long employeeId){
            return availabilityService.getAvailabilityByEmployeeId(employeeId);
    }
    @PostMapping("/update")
    public Availability updateAvailability(@RequestBody Availability availability) {
        return availabilityService.updateAvailability(availability);
    }
    @PostMapping("/delete")
    public void deleteAvailability(@RequestParam Long id) {
        availabilityService.deleteAvailability(id);
    }
    
    

}

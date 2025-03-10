package com.shifts.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.backend.model.TimeOffRequest;
import com.shifts.backend.service.service.EmployeeService;
import com.shifts.backend.service.service.TimeOffRequestService;

//basic CRUD operations for TimeOffRequest class
@RestController
@RequestMapping("/api/timeOffRequest")
public class TimeOffRequestController {
    private final TimeOffRequestService timeOffRequestService;
    private final EmployeeService employeeService;

    TimeOffRequestController(TimeOffRequestService timeOffRequestService, EmployeeService employeeService) {
        this.timeOffRequestService = timeOffRequestService;
        this.employeeService = employeeService;
    }

    @PostMapping("/{id}")
    public TimeOffRequest saveTimeOffRequest(@RequestBody TimeOffRequest timeOffRequest, @PathVariable("id") Long id) {
        timeOffRequest.setEmployee(employeeService.getEmployeeById(id));
        timeOffRequestService.saveTimeOffRequest(timeOffRequest);
        return timeOffRequest;
    }

    @GetMapping("/")
    public List<TimeOffRequest> getAllTimeOffRequests(){
        return timeOffRequestService.getAllTimeOffRequests();
    }

    @GetMapping("/{id}")
    public TimeOffRequest getTimeOffRequestById(@PathVariable("id") Long id) {
        return timeOffRequestService.getTimeOffRequestById(id);
    }

    @GetMapping("/employee/{id}")
    public List<TimeOffRequest> getAllTimeOffRequestsByEmployeeId(@PathVariable("id") Long employeeId) {
        return timeOffRequestService.getAllTimeOffRequestsByEmployeeId(employeeId);
    }

    @PutMapping("/{id}")
    public TimeOffRequest updaTimeOffRequest(@RequestBody TimeOffRequest timeOffRequest, @PathVariable("id") Long id){
        return timeOffRequestService.updateTimeOffRequest(timeOffRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTimeOffRequest(@PathVariable("id") Long id) {
        timeOffRequestService.deleteTimeOffRequest(id);
    }
}

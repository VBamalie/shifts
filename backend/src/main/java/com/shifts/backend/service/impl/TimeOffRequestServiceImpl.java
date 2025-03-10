package com.shifts.backend.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.shifts.backend.model.TimeOffRequest;
import com.shifts.backend.repository.EmployeeRepo;
import com.shifts.backend.repository.TimeOffRequestRepo;
import com.shifts.backend.service.service.TimeOffRequestService;

//Crud operations for the TimeOffRequest class
@Service
public class TimeOffRequestServiceImpl implements TimeOffRequestService {

    private final TimeOffRequestRepo timeOffRequestRepo;
    private final EmployeeRepo employeeRepo;

    TimeOffRequestServiceImpl(TimeOffRequestRepo timeOffRequestRepo, EmployeeRepo employeeRepo){
        this.timeOffRequestRepo = timeOffRequestRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public TimeOffRequest saveTimeOffRequest(TimeOffRequest timeOffRequest) {
        return timeOffRequestRepo.save(timeOffRequest);
    }

    @Override
    public List<TimeOffRequest> getAllTimeOffRequests() {
        return timeOffRequestRepo.findAll();
    }

    @Override
    public TimeOffRequest getTimeOffRequestById(Long id) {
        return timeOffRequestRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("TimeOffRequest not found with id: " + id));
    }

    @Override
    public List<TimeOffRequest> getAllTimeOffRequestsByEmployeeId(Long employeeId) {
        return employeeRepo.findById(employeeId)
                .map(timeOffRequestRepo::findByEmployee)                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
    }

    @Override
    public TimeOffRequest updateTimeOffRequest(TimeOffRequest timeOffRequest, Long id) {
        return timeOffRequestRepo.findById(id)
        .map(db -> {
            if(Objects.nonNull(timeOffRequest.getFirstDate())){
                db.setFirstDate(timeOffRequest.getFirstDate());
            }
            if(Objects.nonNull(timeOffRequest.getWeekDayEnum())){
                db.setWeekDayEnum(timeOffRequest.getWeekDayEnum());
            }            
            return timeOffRequestRepo.save(db);
        })
        .orElseThrow(() -> new RuntimeException("TimeOffRequest not found with id: " + id));
    }

    @Override
    public String deleteTimeOffRequest(Long id) {
        timeOffRequestRepo.deleteById(id);
        return "TimeOffRequest deleted successfully";
    }
}

package com.shifts.backend.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifts.backend.model.TimeOffRequest;
import com.shifts.backend.repository.EmployeeRepo;
import com.shifts.backend.repository.TimeOffRequestRepo;
import com.shifts.backend.service.service.TimeOffRequestService;

//Crud operations for the TimeOffRequest class
@Service
public class TimeOffRequestServiceImpl implements TimeOffRequestService {

    @Autowired
    private TimeOffRequestRepo timeOffRequestRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

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
        return timeOffRequestRepo.findById(id).get();
    }

    @Override
    public List<TimeOffRequest> getAllTimeOffRequestsByEmployeeId(Long employeeId) {
        return timeOffRequestRepo.findByEmployee(employeeRepo.findById(employeeId).get());
    }

    @Override
    public TimeOffRequest updateTimeOffRequest(TimeOffRequest timeOffRequest, Long id) {
        return timeOffRequestRepo.findById(id)
        .map(db -> {
            if(Objects.nonNull(timeOffRequest.getDate())){
                db.setDate(timeOffRequest.getDate());
            }
            if(Objects.nonNull(timeOffRequest.getWeekDayEnum())){
                db.setWeekDayEnum(timeOffRequest.getWeekDayEnum());
            }            
            return timeOffRequestRepo.save(db);
        })
        .orElseThrow(() -> new RuntimeException("TimeOffRequest not found with id: " + id));
    }

    @Override
    public void deleteTimeOffRequest(Long id) {
        timeOffRequestRepo.deleteById(id);
    }
}

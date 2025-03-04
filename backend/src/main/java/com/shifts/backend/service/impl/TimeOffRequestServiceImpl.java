package com.shifts.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifts.backend.model.TimeOffRequest;
import com.shifts.backend.repository.TimeOffRequestRepo;
import com.shifts.backend.service.service.TimeOffRequestService;

@Service
public class TimeOffRequestServiceImpl implements TimeOffRequestService {

    @Autowired
    private TimeOffRequestRepo timeOffRequestRepo;

    @Override
    public TimeOffRequest saveTimeOffRequest(TimeOffRequest timeOffRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveTimeOffRequest'");
    }

    @Override
    public List<TimeOffRequest> getAllTimeOffRequests() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllTimeOffRequests'");
    }

    @Override
    public TimeOffRequest getTimeOffRequestById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTimeOffRequestById'");
    }

    @Override
    public List<TimeOffRequest> getAllTimeOffRequestsByEmployeeId(Long employeeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllTimeOffRequestsByEmployeeId'");
    }

    @Override
    public TimeOffRequest updateTimeOffRequest(TimeOffRequest timeOffRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTimeOffRequest'");
    }

    @Override
    public void deleteTimeOffRequest(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTimeOffRequest'");
    }



}

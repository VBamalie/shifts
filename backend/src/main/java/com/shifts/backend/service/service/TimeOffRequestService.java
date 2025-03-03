package com.shifts.backend.service.service;

import java.util.List;

import com.shifts.backend.model.TimeOffRequest;

//this is the interface used for time off request service impl
public interface TimeOffRequestService {
    TimeOffRequest saveTimeOffRequest(TimeOffRequest timeOffRequest);
    List<TimeOffRequest> getAllTimeOffRequests();
    TimeOffRequest getTimeOffRequestById(int id);
    List<TimeOffRequest> getAllTimeOffRequestsByEmployeeId(int employeeId);
    TimeOffRequest updateTimeOffRequest(TimeOffRequest timeOffRequest);
    void deleteTimeOffRequest(int id);

}

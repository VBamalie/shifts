package com.shifts.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifts.backend.repository.AvailabilityRepo;
import com.shifts.backend.repository.CalendarRepo;
import com.shifts.backend.repository.EmployeeRepo;
import com.shifts.backend.repository.EmployeeShiftRepo;
import com.shifts.backend.repository.ShiftRepo;
import com.shifts.backend.repository.TimeBlockRepo;
import com.shifts.backend.repository.TimeOffRequestRepo;

@Service
public class AutoCreateService {
    @Autowired
    private AvailabilityRepo availabilityRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private CalendarRepo calendarRepo;
    @Autowired
    private ShiftRepo shiftRepo;
    @Autowired
    private EmployeeShiftRepo employeeShiftRepo;
    @Autowired
    private TimeBlockRepo timeBlockRepo;
    @Autowired
    private TimeOffRequestRepo timeOffRequestRepo;


    public void autoCreate() {
        // TODO Auto-generated method stub

    }

}

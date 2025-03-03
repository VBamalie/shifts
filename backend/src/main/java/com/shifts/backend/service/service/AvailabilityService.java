package com.shifts.backend.service.service;

import java.util.List;

import com.shifts.backend.model.Availability;

//this is the interface used for availabilityserviceimpl
public interface AvailabilityService {
    Availability saveAvailability(Availability availability);
    List<Availability> getAllAvailabilities();
    Availability getAvailabilityById(int id);
    Availability getAvailabilityByEmployeeId(int employeeId);
    Availability updateAvailability(Availability availability);
    void deleteAvailability(int id);
}

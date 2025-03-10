package com.shifts.backend.service.service;

import java.util.List;

import com.shifts.backend.model.EmployeeAvailability;

//this is the interface used for availabilityserviceimpl
public interface EmployeeAvailabilityService {
    EmployeeAvailability saveAvailability(EmployeeAvailability availability);
    List<EmployeeAvailability> getAllAvailabilities();
    EmployeeAvailability getAvailabilityById(Long id);
    EmployeeAvailability updateAvailability(EmployeeAvailability availability, Long id);
}

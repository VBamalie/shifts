package com.shifts.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifts.backend.model.Availability;
import com.shifts.backend.repository.AvailabilityRepo;
import com.shifts.backend.service.service.AvailabilityService;

@Service
//Crud operations for the Availability class
public class AvailabilityServiceImpl implements AvailabilityService {
    @Autowired
    private  AvailabilityRepo availabilityRepo;

    @Override
    public Availability saveAvailability(Availability availability) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAvailability'");
    }

    @Override
    public List<Availability> getAllAvailabilities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllAvailabilities'");
    }

    @Override
    public Availability getAvailabilityById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailabilityById'");
    }

    @Override
    public Availability getAvailabilityByEmployeeId(Long employeeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailabilityByEmployeeId'");
    }

    @Override
    public Availability updateAvailability(Availability availability) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAvailability'");
    }

    @Override
    public void deleteAvailability(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAvailability'");
    }



}

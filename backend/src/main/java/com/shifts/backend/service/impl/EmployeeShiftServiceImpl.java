package com.shifts.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifts.backend.model.EmployeeShift;
import com.shifts.backend.repository.EmployeeShiftRepo;
import com.shifts.backend.service.service.EmployeeShiftService;

@Service
public class EmployeeShiftServiceImpl implements EmployeeShiftService {

    @Autowired
    private EmployeeShiftRepo employeeShiftRepo;

    @Override
    public EmployeeShift saveEmployeeShift(EmployeeShift employeeShift) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveEmployeeShift'");
    }

    @Override
    public List<EmployeeShift> getAllEmployeeShifts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllEmployeeShifts'");
    }

    @Override
    public EmployeeShift getEmployeeShiftById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEmployeeShiftById'");
    }

    @Override
    public EmployeeShift updateEmployeeShift(EmployeeShift employeeShift) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEmployeeShift'");
    }

    @Override
    public void deleteEmployeeShift(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEmployeeShift'");
    }

    @Override
    public List<EmployeeShift> getAllEmployeeShiftsByShiftId(int shiftId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllEmployeeShiftsByShiftId'");
    }

}

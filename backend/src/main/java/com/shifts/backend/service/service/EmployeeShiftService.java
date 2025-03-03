package com.shifts.backend.service.service;

import java.util.List;

import com.shifts.backend.model.EmployeeShift;

//this is the interface used for employee shift service impl
public interface EmployeeShiftService {
    EmployeeShift saveEmployeeShift(EmployeeShift employeeShift);
    List<EmployeeShift> getAllEmployeeShifts();
    EmployeeShift getEmployeeShiftById(int id);
    List<EmployeeShift> getAllEmployeeShiftsByShiftId(int shiftId);
    EmployeeShift updateEmployeeShift(EmployeeShift employeeShift);
    void deleteEmployeeShift(int id);
}

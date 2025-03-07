package com.shifts.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifts.backend.model.Shift;
import com.shifts.backend.repository.ShiftRepo;
import com.shifts.backend.service.service.ShiftService;

@Service
public class ShiftServiceImpl implements ShiftService{

    @Autowired
    private ShiftRepo shiftRepo;

    @Override
    public Shift saveShift(Shift shift) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveShift'");
    }

    @Override
    public List<Shift> getAllShifts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllShifts'");
    }

    @Override
    public Shift getShiftById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getShiftById'");
    }

    @Override
    public List<Shift> getAllShiftsByCalendarId(Long calendarId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllShiftsByCalendarId'");
    }

    @Override
    public Shift updateShift(Shift shift) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateShift'");
    }

    @Override
    public void deleteShift(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteShift'");
    }

    @Override
    public boolean hasFilledShifts(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filledShifts'");
    }
}

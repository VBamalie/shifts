package com.shifts.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifts.backend.model.Shift;
import com.shifts.backend.repository.ShiftRepo;
import com.shifts.backend.service.service.ShiftService;

//Crud operations for the Shift class
@Service
public class ShiftServiceImpl implements ShiftService{

    @Autowired
    private ShiftRepo shiftRepo;

    @Override
    public Shift saveShift(Shift shift) {
        return shiftRepo.save(shift);
    }

    @Override
    public List<Shift> getAllShifts() {
        return shiftRepo.findAll();
    }

    @Override
    public Shift getShiftById(Long id) {
        return shiftRepo.findById(id).get();
    }

    @Override
    public List<Shift> getAllShiftsByCalendarId(Long calendarId) {
        return shiftRepo.findByCalendar(calendarId);
    }

    @Override
    public void deleteShift(Long id) {
        shiftRepo.deleteById(id);
    }

    @Override
    public Shift updateShift(Shift shift, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateShift'");
    }

    @Override
    public boolean hasFilledShifts(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filledShifts'");
    }


}

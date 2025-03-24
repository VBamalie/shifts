package com.shifts.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shifts.backend.model.Shift;
import com.shifts.backend.model.TimeBlock;
import com.shifts.backend.repository.CalendarRepo;
import com.shifts.backend.repository.ShiftRepo;
import com.shifts.backend.repository.TimeBlockRepo;
import com.shifts.backend.service.service.ShiftService;

//Crud operations for the Shift class
@Service
public class ShiftServiceImpl implements ShiftService{

    private final ShiftRepo shiftRepo;
    private final CalendarRepo calendarRepo;
    private final TimeBlockRepo timeBlockRepo;

    ShiftServiceImpl(ShiftRepo shiftRepo, CalendarRepo calendarRepo, TimeBlockRepo timeBlockRepo){
        this.shiftRepo = shiftRepo;
        this.calendarRepo = calendarRepo;
        this.timeBlockRepo = timeBlockRepo;
    }

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
        return shiftRepo.findByCalendar(calendarRepo.findById(calendarId).get());
    }

    @Override
    public String deleteShift(Long id) {
        shiftRepo.deleteById(id);
        return "Shift deleted with id: " + id;
    }

    @Override
    public boolean hasFilledShifts(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filledShifts'");
    }

    @Override
    public Shift getShiftByTimeBlockId(Long timeBlockId) {
        return shiftRepo.findByTimeBlock(timeBlockRepo.findById(timeBlockId).get());
    }

    @Override
    public List<Shift> getShiftsByFirstDat(Long calenderId, String firstDate) {
        return shiftRepo.findByCalendarAndFirstDate(calendarRepo.findById(calenderId).get(), firstDate);
    }

    @Override
    public String createShifts(Long calendarId, String date) {
        List<TimeBlock> timeBlockList = timeBlockRepo.findByCalendar(calendarRepo.findById(calendarId).get());
        timeBlockList.forEach(timeBlock -> {
            for(int i = 0; i < timeBlock.getShiftsRequired(); i++) {
                Shift shift = new Shift();
                shift.setTimeBlock(timeBlock);
                shift.setFirstDate(date);
                shiftRepo.save(shift);
            }
        });
        return "Shifts created";
    }


}

package com.shifts.backend.service.service;

import java.util.List;

import com.shifts.backend.model.Shift;

//this is the interface used for shift service impl
public interface ShiftService {
    Shift saveShift(Shift shift);
    List<Shift> getAllShifts();
    Shift getShiftById(Long id);
    List<Shift> getAllShiftsByCalendarId(Long calendarId);
    Shift getShiftByTimeBlockId(Long timeBlockId);
    List<Shift> getShiftsByFirstDat(Long calenderId, String firstDate);
    String deleteShift(Long id);
    boolean hasFilledShifts(Long id);
    String createShifts(Long id, String date);

}

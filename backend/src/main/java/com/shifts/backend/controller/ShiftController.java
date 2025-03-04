package com.shifts.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.backend.model.Shift;
import com.shifts.backend.service.service.ShiftService;

@RestController
@RequestMapping("/api/shift")
public class ShiftController {
    @Autowired
    private ShiftService shiftService;

    @PostMapping("/")
    public Shift saveShift(Shift shift) {
        //TODO: process POST request
        return shift;
    }
    @GetMapping("/")
    public List<Shift> getAllShifts() {
        return shiftService.getAllShifts();
    }
    @GetMapping("/{id}")
    public Shift getShiftById(Long id) {
        return shiftService.getShiftById(id);
    }
    @PostMapping("/update")
    public Shift updateShift(Shift shift) {
        return shiftService.updateShift(shift);
    }
    @PostMapping("/delete")
    public void deleteShift(Long id) {
        shiftService.deleteShift(id);
    }
    @GetMapping("/calendar/{id}")
    public List<Shift> getAllShiftsByCalendarId(Long calendarId) {
        return shiftService.getAllShiftsByCalendarId(calendarId);
    }
    @GetMapping("/filled/{id}")
    public boolean hasFilledShifts(Long id) {
        return shiftService.hasFilledShifts(id);
    }
}

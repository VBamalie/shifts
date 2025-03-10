package com.shifts.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.backend.model.Shift;
import com.shifts.backend.service.service.TimeBlockService;
import com.shifts.backend.service.service.ShiftService;

@RestController
@RequestMapping("/api/shift")
public class ShiftController {
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private TimeBlockService timeBlockService;

    @PostMapping("/{id}")
    public Shift saveShift(@RequestBody Shift shift, @PathVariable("id") Long id) {
        shift.setTimeBlock(timeBlockService.getTimeBlockById(id));
        shift.setCalendar(shift.getTimeBlock().getCalendar());
        return shiftService.saveShift(shift);
    }
    @GetMapping("/")
    public List<Shift> getAllShifts() {
        return shiftService.getAllShifts();
    }
    @GetMapping("/{id}")
    public Shift getShiftById(@PathVariable("id") Long id) {
        return shiftService.getShiftById(id);
    }

    //No Put method because employees will be added from the employee controller. Every other field is final

    @DeleteMapping("/{id}")
    public void deleteShift(@PathVariable Long id) {
        shiftService.deleteShift(id);
    }
    @GetMapping("/calendar/{id}")
    public List<Shift> getAllShiftsByCalendarId(@PathVariable("id") Long calendarId) {
        return shiftService.getAllShiftsByCalendarId(calendarId);
    }
    @GetMapping("/timeblock/{id}")
    public Shift getShiftByTimeBlockID(@PathVariable("id") Long timeBlockId) {
        return shiftService.getShiftByTimeBlockId(timeBlockId);
    }
    @GetMapping("/filled/{id}")
    public boolean hasFilledShifts(Long id) {
        return shiftService.hasFilledShifts(id);
    }
}

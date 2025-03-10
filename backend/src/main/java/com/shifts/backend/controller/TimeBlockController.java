package com.shifts.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.backend.model.TimeBlock;
import com.shifts.backend.service.service.CalendarService;
import com.shifts.backend.service.service.TimeBlockService;

@RestController
@RequestMapping("/api/timeblock")
public class TimeBlockController {
    private final TimeBlockService timeBlockService;
    private final CalendarService calendarService;

    TimeBlockController(TimeBlockService timeBlockService, CalendarService calendarService) {
        this.timeBlockService = timeBlockService;
        this.calendarService = calendarService;
    }

    @PostMapping("/{id}")
    public TimeBlock saveTimeBlock(@RequestBody TimeBlock timeBlock, @PathVariable("id") Long id) {
        timeBlock.setCalendar(calendarService.getCalendarById(id));
        timeBlockService.saveTimeBlock(timeBlock);
        return timeBlock;
        }
    @GetMapping("/")
    public List<TimeBlock> getAllTimeBlocks() {
        return timeBlockService.getAllTimeBlocks();
    }
    @GetMapping("/{id}")
    public TimeBlock getTimeBlockById(@PathVariable("id")Long id) {
        return timeBlockService.getTimeBlockById(id);
    }
    @GetMapping("/calendar/{id}")
    public List<TimeBlock> getAllTimeBlocksByCalendarId(@PathVariable("id") Long calendarId) {
        return timeBlockService.getAllTimeBlocksByCalendarId(calendarId);
    }
    @PutMapping("/{id}")
    public TimeBlock updateTimeBlock(@RequestBody TimeBlock timeBlock , @PathVariable("id") Long id) {
        return timeBlockService.updateTimeBlock(timeBlock, id);
    }
    @DeleteMapping("/{id}")
    public void deleteTimeBlock(@PathVariable("id") Long id) {
        timeBlockService.deleteTimeBlock(id);
    }


}

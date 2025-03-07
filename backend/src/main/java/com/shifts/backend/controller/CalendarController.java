package com.shifts.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.shifts.backend.model.Calendar;
import com.shifts.backend.service.service.CalendarService;
//basic CRUD operations for the Calendar class
@RestController
@RequestMapping("/api/calendar")
public class CalendarController {
    @Autowired
    private CalendarService calendarService;
    @PostMapping("/")
    public Calendar saveCalendar( @RequestBody Calendar calendar) {
        calendarService.saveCalendar(calendar);
        return calendar;
    }
    @GetMapping("/")
    public List<Calendar> getAllCalendars() {
        return calendarService.getAllCalendars();
    }
    @GetMapping("/{id}")
    public Calendar getCalendarById(@PathVariable("id") Long id) {
        return calendarService.getCalendarById(id);
    }
    @PutMapping("/{id}")
    public Calendar updateCalendar( @RequestBody Calendar calendar, @PathVariable("id") Long id) {
        return calendarService.updateCalendar(calendar, id);
    }
    @PostMapping("/delete")
    public void deleteCalendar(Long id) {
        calendarService.deleteCalendar(id);
    }
}

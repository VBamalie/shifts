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

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {
    @Autowired
    private CalendarService calendarService;
    @PostMapping("/")
    public Calendar saveCalendar(Calendar calendar) {
        calendarService.saveCalendar(calendar);
        return calendar;
    }
    @GetMapping("/")
    public List<Calendar> getAllCalendars() {
        return calendarService.getAllCalendars();
    }
    @GetMapping("/{id}")
    public Calendar getCalendarById(Long id) {
        return calendarService.getCalendarById(id);
    }
    @PutMapping("/update/{id}")
    public Calendar updateCalendar(Calendar calendar, @RequestBody @PathVariable("id")Long id) {
        return calendarService.updateCalendar(calendar, id);
    }
    @PostMapping("/delete")
    public void deleteCalendar(Long id) {
        calendarService.deleteCalendar(id);
    }
}

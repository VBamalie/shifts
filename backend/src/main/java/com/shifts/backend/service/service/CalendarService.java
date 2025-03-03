package com.shifts.backend.service.service;

import java.util.List;

import com.shifts.backend.model.Calendar;

//this is the interface used for calendar service impl
public interface CalendarService {
    Calendar saveCalendar(Calendar calendar);
    List<Calendar> getAllCalendars();
    Calendar getCalendarById(int id);
    Calendar updateCalendar(Calendar calendar);
    void deleteCalendar(int id);
}

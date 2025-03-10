package com.shifts.backend.service.service;

import java.util.List;

import com.shifts.backend.model.Calendar;

//this is the interface used for calendar service impl
public interface CalendarService {
    Calendar saveCalendar(Calendar calendar);
    List<Calendar> getAllCalendars();
    Calendar getCalendarById(Long id);
    Calendar updateCalendar(Calendar calendar, long id);;
    String deleteCalendar(Long id);
}

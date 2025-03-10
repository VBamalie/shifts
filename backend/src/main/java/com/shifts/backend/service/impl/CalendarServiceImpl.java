package com.shifts.backend.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.shifts.backend.model.Calendar;
import com.shifts.backend.repository.CalendarRepo;
import com.shifts.backend.service.service.CalendarService;

@Service
///Crud operations for the Calendar class
public class CalendarServiceImpl implements CalendarService {
    
    private final CalendarRepo calendarRepo;

    public CalendarServiceImpl(CalendarRepo calendarRepo) {
        this.calendarRepo = calendarRepo;
    }

    @Override
    public Calendar saveCalendar(Calendar calendar) {
        return calendarRepo.save(calendar);
    }
    

    @Override
    public List<Calendar> getAllCalendars() {
        return calendarRepo.findAll();
    }

    @Override
    public Calendar getCalendarById(Long id) {
        return calendarRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Calendar not found with id: " + id));
    }

    @Override
    public Calendar updateCalendar(Calendar calendar, long id) {
        return calendarRepo.findById(id)
            .map(db -> {
                if(Objects.nonNull(calendar.getBusinessName()) && !"".equalsIgnoreCase(calendar.getBusinessName())){
                    db.setBusinessName(calendar.getBusinessName());
                }
                return calendarRepo.save(db);
            })
            .orElseThrow(() -> new RuntimeException("Calendar not found with id: " + id));
    }

    @Override
    public String deleteCalendar(Long id) {
        calendarRepo.deleteById(id);
        return "Calendar deleted with id: " + id;
    }


    
}
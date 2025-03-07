package com.shifts.backend.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifts.backend.model.Calendar;
import com.shifts.backend.repository.CalendarRepo;
import com.shifts.backend.service.service.CalendarService;

@Service
///Crud operations for the Calendar class
public class CalendarServiceImpl implements CalendarService {
    
    @Autowired
    private CalendarRepo calendarRepo;

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
        return calendarRepo.findById(id).get();
    }

    @Override
    public Calendar updateCalendar(Calendar calendar, long id) {
        Calendar db = calendarRepo.findById(id).get();
        if(Objects.nonNull(calendar.getBusinessName()) && !"".equalsIgnoreCase(calendar.getBusinessName())){
            db.setBusinessName(calendar.getBusinessName());
        }

        return calendarRepo.save(db);
    }

    @Override
    public void deleteCalendar(Long id) {
        calendarRepo.deleteById(id);
        //TODO: add functionality to delete all shifts, employees and timeblocks associated with the calendar
    }


    
}

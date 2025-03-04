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

    public void createCalendarTest(){
        Calendar calendar = new Calendar();
        calendar.setBusinessName("Test Calendar");
    }

    @Override
    public Calendar saveCalendar(Calendar calendar) {
        calendarRepo.save(calendar);
        return calendar;
    }

    @Override
    public List<Calendar> getAllCalendars() {
        createCalendarTest();
        return calendarRepo.findAll();
    }

    @Override
    public Calendar getCalendarById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCalendarById'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCalendar'");
    }


    
}

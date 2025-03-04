package com.shifts.backend.service.impl;

import java.util.List;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveCalendar'");
    }

    @Override
    public List<Calendar> getAllCalendars() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCalendars'");
    }

    @Override
    public Calendar getCalendarById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCalendarById'");
    }

    @Override
    public Calendar updateCalendar(Calendar calendar) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCalendar'");
    }

    @Override
    public void deleteCalendar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCalendar'");
    }


    
}

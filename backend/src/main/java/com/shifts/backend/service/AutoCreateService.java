package com.shifts.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifts.backend.model.Calendar;
import com.shifts.backend.repository.CalendarRepo;

@Service
public class AutoCreateService {

    @Autowired
    private CalendarRepo calendarRepo;


    public void autoCreate(long id, int date) {
        Calendar calendar = calendarRepo.findById(id).orElse(null);
        calendar.createShiftFromTimeBlock(date);
    }

}

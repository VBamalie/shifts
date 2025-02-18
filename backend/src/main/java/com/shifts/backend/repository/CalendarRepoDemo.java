package com.shifts.backend.repository;

import java.util.ArrayList;
import java.util.List;

import com.shifts.backend.model.Calendar;

//Stand in for the database and DTO for the calendar
//TODO: Add a method to get a calendar by id.
    //TODO: Add a method to update a calendar by id.
    //TODO: Add a method to delete a calendar by id.
    //TODO: add a method that does the autocreate function then returns the calendar.
    public class CalendarRepoDemo {
        private final List<Calendar> calendarList = new ArrayList<>();
        public CalendarRepoDemo(){
    
        }
        public List<Calendar> findAll(){
            return calendarList;
        }
    
        public void save( Calendar calendar){
            calendarList.add(calendar);
        }
    
    
    }

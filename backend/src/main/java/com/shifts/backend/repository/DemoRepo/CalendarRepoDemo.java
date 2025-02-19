package com.shifts.backend.repository.DemoRepo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shifts.backend.model.Calendar;

import jakarta.annotation.PostConstruct;

//Stand in for the database and DTO for the calendar
//TODO: Add a method to get a calendar by id.
    //TODO: Add a method to update a calendar by id.
    //TODO: Add a method to delete a calendar by id.
    //TODO: add a method that does the autocreate function then returns the calendar.
    @Repository
    public class CalendarRepoDemo {
        private final List<Calendar> calendarList = new ArrayList<>();
        public CalendarRepoDemo(){
    
        }
        public List<Calendar> findAll(){
            return calendarList;
        }
        public Calendar findById(int id){
            return calendarList.stream()
            .filter(calendar -> calendar.getId() == id)
            .findFirst()
            .orElse(null);
        }
        public void update(Calendar calendar){
            int index = calendarList.indexOf(calendar);
            calendarList.set(index, calendar);
        }
        public void save( Calendar calendar){
            calendarList.add(calendar);
        }
        public void deleteById(int id){
            calendarList.removeIf(calendar -> calendar.getId() == id);
        }
    @PostConstruct
    private void init(){
        Calendar calendar = new Calendar(1, "Test Business");
        calendarList.add(calendar);
    }
    
    }

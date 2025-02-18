package com.shifts.backend.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.backend.model.Calendar;
import com.shifts.backend.repository.CalendarRepoDemo;



//This is the controller for the calendar. It is responsible for handling all requests to the /api/calendar endpoint. The front end will send a request to this endpoint to get all calendars, create a new calendar, or update a calendar.
@RestController
@RequestMapping("/api/calendar")
public class CalendarController {
    private final CalendarRepoDemo repository;

    public CalendarController( CalendarRepoDemo repository){
        this.repository = repository;
    }
    //This method is responsible for handling GET requests to the /api/calendar endpoint. It returns a list of all calendars. We likely wont use this method in the front end.
    @GetMapping("")
    public List<Calendar> getAllCalendars(){
        return repository.findAll();
    }
    //TODO: Add a method to get a calendar by id.
    //TODO: Add a method to update a calendar by id.
    //TODO: Add a method to delete a calendar by id.
    //TODO: add a method that does the autocreate function then returns the calendar.

    
    @PostMapping("")
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public void create(@RequestBody Calendar calendar){
        repository.save(calendar);
    }
}


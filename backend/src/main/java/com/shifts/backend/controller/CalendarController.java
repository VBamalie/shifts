package com.shifts.backend.controller;


import java.util.List;

// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.backend.model.Calendar;
import com.shifts.backend.repository.CalendarRepoDemo;



//This is the controller for the calendar. It is responsible for handling all requests to the /api/calendar endpoint. The front end will send a request to this endpoint to get all calendars, create a new calendar, or update a calendar.
@RestController
// @CrossOrigin("http://localhost:5173")
@RequestMapping("/api/calendar")
public class CalendarController {
    private final CalendarRepoDemo repository;

    public CalendarController( CalendarRepoDemo repository){
        this.repository = repository;
        //dummy data for testing
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
    @GetMapping("/{id}")
    public Calendar getCalendarById(int id){
        return repository.findById(id);
    }
    //This method is responsible for handling POST requests to the /api/calendar endpoint. It creates a new calendar.
    @PostMapping("")
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public void create(@RequestBody Calendar calendar){
        repository.save(calendar);
    }
    //This method is responsible for handling PUT requests to the /api/calendar endpoint. It updates a calendar.
    @PutMapping("/{id}")
    public void update(@RequestBody Calendar calendar){
        repository.update(calendar);
    }
    //This method is responsible for handling DELETE requests to the /api/calendar endpoint. It deletes a calendar.
    @DeleteMapping("/{id}")
    public void delete(@RequestBody Calendar calendar){
        repository.deleteById(calendar.getId());
    }
}


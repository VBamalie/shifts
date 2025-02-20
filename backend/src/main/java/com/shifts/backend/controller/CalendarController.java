package com.shifts.backend.controller;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.backend.model.Calendar;
import com.shifts.backend.repository.DemoRepo.CalendarRepoDemo;



//This is the controller for the calendar class. It is responsible for handling all requests to the /api/calendar endpoint. The front end will send a request to this endpoint to get all calendars, create a new calendar, or update a calendar. It will also handle the call for the autocreate function.
@RestController
@CrossOrigin(origins = "http://localhost:5173")//all controllers need this in order to work with the front end.
@RequestMapping("/api/calendar")
public class CalendarController {
    private final CalendarRepoDemo repository;

    public CalendarController( CalendarRepoDemo repository){
        this.repository = repository;
    }

    //This method is responsible for handling GET requests to the /api/calendar endpoint. It returns a list of all calendars. We likely wont use this method in the front end.
    //this method also has the call for the autocreate function.
    @GetMapping("")
    public List<Calendar> getAllCalendars(){
        return repository.findAll();
    }

    //this method is responsible for getting a single calendar object by its id
    @GetMapping("/{id}")
    public Calendar getCalendarById(int id){
        return repository.findById(id);
    }

    //This method is responsible for handling POST requests to the /api/calendar endpoint. It creates a new calendar.
    //TODO: consider adding a calendar constroctor that will also make the first employee and all the timeblocks at the same time as creating the calendar.
    @PostMapping("")
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public void create(@RequestBody Calendar calendar){
        repository.save(calendar);
    }

    //This method is responsible for handling update requests to a calendar.
    @PutMapping("/{id}")
    public void update(@RequestBody Calendar calendar){
        repository.update(calendar);
    }
    
    //This method is responsible for deleting a calendar.
    //TODO: add to this method deleting all timeblocks, employees, and shifts associated with the calendar id.
    @DeleteMapping("/{id}")
    public void delete(@RequestBody Calendar calendar){
        repository.deleteById(calendar.getId());
    }

    //this method is responsible for handling the call for the autocreate function.
    @GetMapping("/autofill/{id}")
    public void autofill(@RequestBody Calendar calendar){
        calendar.autofillShifts();
        //TODO: Should the automatic refresh of the page be handled here or in the front end?
    }
}


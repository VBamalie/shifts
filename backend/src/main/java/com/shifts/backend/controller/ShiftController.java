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

import com.shifts.backend.model.Shift;
import com.shifts.backend.repository.DemoRepo.ShiftRepoDemo;

//This is the controller for the shift objects. It is responsible for handling all requests to the /api/shift endpoint. The front end will send a request to this endpoint to get all shifts, create a new shift, update a shift, and delete a shift.
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/shift")
public class ShiftController {
    private final ShiftRepoDemo repository;
    public ShiftController(ShiftRepoDemo repository){
        this.repository = repository;
    }
    //This grabs all shifts in the database. this will never be used in the front end.
    @GetMapping("")
    public List<Shift> getAllShifts(){
        return null;
        //TODO:Write
    }
    //This grabs a shift by its id.
    @GetMapping("/{id}")
    public Shift getShiftById(int id){
        return null;
        //TODO:Write
    }
    //This grabs all shifts with a certain calendar id.
    @GetMapping("Calendar/{id}")
    public List<Shift> getShiftsByCalendarId(int id){
        return null;
        //TODO:Write
    }
    //This grabs all shifts with a certain date. The dates are always the first date of the week regardless of what day the shift lands on. the front end will sort through the different days
    @GetMapping("date/{date}")
    public List<Shift> getShiftsByDate(int date){
        return null;
        //TODO:Write
    }
    //This creates a new shift.
    @PostMapping("")
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public void create(@RequestBody Shift shift){
        //TODO:Write
    }
    //This updates a shift.
    @PutMapping("/{id}")
    public void update(@RequestBody Shift shift){
        //Write
    }
    //This deletes a shift.
    @DeleteMapping("/{id}")
    public void delete(int id){
        //TODO:Write
    }
}

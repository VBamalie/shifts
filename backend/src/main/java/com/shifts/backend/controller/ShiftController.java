package com.shifts.backend.controller;

import java.util.List;

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

@RestController
@RequestMapping("/api/shift")
public class ShiftController {
    private final ShiftRepoDemo repository;
    public ShiftController(ShiftRepoDemo repository){
        this.repository = repository;
    }

    @GetMapping("")
    public List<Shift> getAllShifts(){
        return null;
        //TODO:Write
    }
    @GetMapping("/{id}")
    public Shift getShiftById(int id){
        return null;
        //TODO:Write
    }
    @GetMapping("Calendar/{id}")
    public List<Shift> getShiftsByCalendarId(int id){
        return null;
        //TODO:Write
    }
    @PostMapping("")
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public void create(@RequestBody Shift shift){
        //TODO:Write
    }
    @PutMapping("/{id}")
    public void update(@RequestBody Shift shift){
        //Write
    }
    @DeleteMapping("/{id}")
    public void delete(int id){
        //TODO:Write
    }
}

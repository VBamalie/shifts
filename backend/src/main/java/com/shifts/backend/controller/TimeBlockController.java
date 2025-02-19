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

import com.shifts.backend.model.TimeBlock;
import com.shifts.backend.repository.DemoRepo.TimeBlockRepoDemo;

///This is the controller for the time block objects. It is responsible for handling all requests to the /api/timeblock endpoint. The front end will send a request to this endpoint to get all time blocks, create a new time block, update a time block, and delete a time block.
//I DONT KNOW IF THIS WILL BE USED.//
@RestController
@RequestMapping("/api/timeblock")
public class TimeBlockController {
    private final TimeBlockRepoDemo repository;
    public TimeBlockController(TimeBlockRepoDemo repository){
        this.repository = repository;
    }
    //This grabs all time blocks in the database. this will never be used in the front end.
    @GetMapping("")
    public List<TimeBlock> getAllTimeBlocks(){
        return null;
        //TODO:Write
    }
    @GetMapping("/{id}")
    public TimeBlock getTimeBlockById(int id){
        return null;
        //TODO:Write
    }
    @GetMapping("/calendar/{id}")
    public List<TimeBlock> getTimeBlocksByCalendarId(int id){
        return null;
        //TODO:Write
    }
    
    @PostMapping("")
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public void create(@RequestBody TimeBlock timeBlock){
        //TODO:Write
    }
    @PutMapping("/{id}")
    public void update(@RequestBody TimeBlock timeBlock){
        //TODO:Write
    }
    @DeleteMapping("/{id}")
    public void delete(int id){
        //TODO:Write
    }
}

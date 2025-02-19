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

@RestController
@RequestMapping("/api/timeblock")
public class TimeBlockController {
    private final TimeBlockRepoDemo repository;
    public TimeBlockController(TimeBlockRepoDemo repository){
        this.repository = repository;
    }
    
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

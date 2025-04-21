package com.shifts.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.backend.service.AutoCreateService;

@RestController
@RequestMapping("api/auto-create")
@CrossOrigin(origins = "*")
public class AutoCreateController {
    @Autowired
    private AutoCreateService autoCreateService;

    @PutMapping("/{id}/{date}")
    public String autoCreate(@PathVariable long id, @PathVariable String date) {        
        boolean status = autoCreateService.autoCreate(id, date);
        if(status) {
            return "Successfully created shifts";
        } else {
            return "Shifts already exist for this week";
        }
        
    }
}

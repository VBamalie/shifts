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
    public void autoCreate(@PathVariable long id, @PathVariable String date) {
        autoCreateService.autoCreate(id, date);
    }
}

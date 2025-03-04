package com.shifts.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifts.backend.service.AutoCreateService;

@RestController
@RequestMapping("/autoCreate")
public class AutoCreateController {
    @Autowired
    private AutoCreateService autoCreateService;

    @RequestMapping("/{id}/{date}")
    public void autoCreate(@PathVariable long id, @PathVariable int date) {
        autoCreateService.autoCreate(id, date);
    }
}

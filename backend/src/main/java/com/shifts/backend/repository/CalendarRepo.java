package com.shifts.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shifts.backend.model.Calendar;

//provides access to the database for the Calendar table
public interface CalendarRepo extends JpaRepository<Calendar, Integer> {

    
} 

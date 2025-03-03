package com.shifts.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shifts.backend.model.Calendar;

//this interface is used to access the database with the help of JPA
@Repository
public interface CalendarRepo extends JpaRepository<Calendar, Integer> {

    
} 

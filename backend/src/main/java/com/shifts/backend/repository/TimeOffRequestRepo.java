package com.shifts.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shifts.backend.model.Employee;
import com.shifts.backend.model.TimeOffRequest;

//this interface is used to access the database with the help of JPA
@Repository
public interface TimeOffRequestRepo extends JpaRepository<TimeOffRequest, Long> {

    List<TimeOffRequest> findByEmployee(Employee employee);//finds all timeoffrequests that belong to a specific employee

    
} 

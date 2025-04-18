package com.shifts.backend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.shifts.backend.repository.EmployeeRepo;

public class EmployeeTest {

    EmployeeRepo employeeRepo;
    
    @Test
    void testHoursWorkedThisWeek() {
        
    }

    @Test
    void testHoursWorkedToday() {

    }

    @Test
    void testIsAvailable() {
       
    }

    @Test
    void testGetFirstName() {
        assertEquals("John" , employeeRepo.findById(1L).get().getFirstName()); 
    }
}

package com.shifts.backend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class CalendarTest {
    //add a list of timeblocks
    TimeBlock timeBlock = new TimeBlock(
        1,
        1,
        2,
        1,
        weekDayEnum.mon);
    ArrayList<TimeBlock> timeBlocks = new ArrayList<>();
    //timeBlocks.add(timeBlock); FIXME: causing error figure it out later
    //add a list of employees
    ArrayList<Employee> employees = new ArrayList<>();
    Calendar calendar = new Calendar(
        1,
        "Test Business",
        employees,
        timeBlocks
        );

    @Test
    void testAutofillShifts() {
        calendar.autofillShifts();
        assertNotNull(calendar.getShifts());
    }
    //TODO: add further tests for autofillShifts

    @Test
    void testCreateShiftFromTimeBlock() {
        ArrayList<Shift> shifts = calendar.createShiftFromTimeBlock(20250101);
        assertNotNull(shifts);
        assertEquals(shifts.get(0).getTimeBlock(), timeBlock);
    }
    //TODO: add further tests for createShiftFromTimeBlock

    @Test
    void testGetBusinessName() {
        assertEquals(calendar.getBusinessName(), "Test Business");
    }

    @Test
    void testGetEmployees() {
        assertEquals(calendar.getEmployees(), employees);
    }

    @Test
    void testGetId() {
        assertEquals(calendar.getId(), 1);
    }

    @Test
    void testGetShifts() {
        //TODO: add dummy shifts to calendar
    }

    @Test
    void testGetTimeBlocks() {
        assertEquals(calendar.getTimeBlocks(), timeBlocks);
    }

    @Test
    void testSetBusinessName() {
        calendar.setBusinessName("New Business Name");
        assertEquals(calendar.getBusinessName(), "New Business Name");
    }

    @Test
    void testSetEmployees() {
        ArrayList<Employee> newEmployees = new ArrayList<>();
        calendar.setEmployees(newEmployees);
        assertEquals(calendar.getEmployees(), newEmployees);
    }

    @Test
    void testSetId() {
        calendar.setId(2);
        assertEquals(calendar.getId(), 2);
    }

    @Test
    void testSetShifts() {
        ArrayList<Shift> newShifts = new ArrayList<>();
        calendar.setShifts(newShifts);
        assertEquals(calendar.getShifts(), newShifts);
    }

    @Test
    void testSetTimeBlocks() {
        ArrayList<TimeBlock> newTimeBlocks = new ArrayList<>();
        calendar.setTimeBlocks(newTimeBlocks);
        assertEquals(calendar.getTimeBlocks(), newTimeBlocks);
    }
}

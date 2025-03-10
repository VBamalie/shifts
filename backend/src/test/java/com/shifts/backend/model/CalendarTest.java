package com.shifts.backend.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class CalendarTest {
    //This is a test object of a calendar
    public Calendar makeCalendar(){
        //create a calendar
        Calendar calendar = new Calendar(1L, "Test Business", null, null, null);
        //create an employee
        Employee employee = new Employee(1L, "John", "Doe", "john.doe@example.com", "password", calendar, null, null, null);
        Availability availability = new Availability(1L, employee, 1.0, 2.0, 3.0,4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0);
        employee.setAvailability(availability);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        calendar.setEmployees(employees);
        //create a time blocks
        TimeBlock timeBlock1 = new TimeBlock(1L, 1.0, 2.0, 1, WeekDayEnum.MON, calendar);
        TimeBlock timeBlock2 = new TimeBlock(2L, 3.0, 4.0, 2, WeekDayEnum.TUE, calendar);
        TimeBlock timeBlock3 = new TimeBlock(3L, 5.0, 6.0, 3, WeekDayEnum.WED, calendar);
        List<TimeBlock> timeBlocks = new ArrayList<>();
        timeBlocks.add(timeBlock1);
        timeBlocks.add(timeBlock2);
        timeBlocks.add(timeBlock3);
        calendar.setTimeBlocks(timeBlocks);
        return calendar;
    }
    @Test
    //Tests that shifts have been filled
    void testAutofillShifts() {
        Calendar calendar = makeCalendar();
        calendar.autofillShifts();
        //check if the shifts are filled
        for (Shift shift : calendar.getShifts()) {
            assert shift.hasFilledShifts();
        }
    }
    //TODO: create more tests for to tell if the shifts are filled validly

    @Test
    //tests to see if empty shifts are created
    void testCreateShiftFromTimeBlock() {
        Calendar calendar = makeCalendar();
        calendar.createShiftFromTimeBlock(01012020);
        assert calendar.getShifts().size() == 3;
    }
}

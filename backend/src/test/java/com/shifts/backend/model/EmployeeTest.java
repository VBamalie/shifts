package com.shifts.backend.model;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;

public class EmployeeTest {
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
        //create a shift
        Shift shift = new Shift(1L, calendar.getTimeBlocks().get(0), 01012020, calendar, employees);
        List<Shift> shifts = new ArrayList<>();
        shifts.add(shift);
        calendar.setShifts(shifts);
        return calendar;
    }
    @Test
    void testAddShift() {
        Calendar calendar = makeCalendar();
        Employee employee = new Employee(1L, "John", "Doe", "john.doe@example.com", "password", calendar, null, null, null);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        
        Shift shift = new Shift(2L, calendar.getTimeBlocks().get(0), 01012020, calendar, employees);
        employee.addShift(shift);
        assert employee.getShifts().contains(shift);
    }

    @Test
    void testHoursWorkedThisWeek() {
        Calendar calendar = makeCalendar();
        assert calendar.getEmployees().get(0).hoursWorkedThisWeek(01012020) == 1.0;
    }

    @Test
    void testHoursWorkedToday() {
        Calendar calendar = makeCalendar();
        assert calendar.getEmployees().get(0).hoursWorkedToday(01012020,WeekDayEnum.MON) == 1.0;
    }

    @Test
    void testIsAvailable() {
        Calendar calendar = makeCalendar();
        assert calendar.getEmployees().get(0).isAvailable(01012020,WeekDayEnum.MON, 1.0, 2.0);
    }

    @Test
    void testRemoveShift() {
        Calendar calendar = makeCalendar();
        Employee employee = new Employee(1L, "John", "Doe", "john.doe@example.com", "password", calendar, null, null, null);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        Shift shift = new Shift(2L, calendar.getTimeBlocks().get(0), 01012020, calendar, employees);
        employee.addShift(shift);
        employee.removeShift(shift);
        assert !employee.getShifts().contains(shift);
    }

    @Test
    void testWorkingAShiftTheyAreUnavailableFor() {
        Calendar calendar = makeCalendar();
        Employee employee = new Employee(1L, "John", "Doe", "john.doe@example.com", "password", calendar, null, null, null);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        Shift shift = new Shift(2L, calendar.getTimeBlocks().get(0), 01012020, calendar, employees);
        employee.addShift(shift);
        //assert that the employee is unavailable for the shift
    }
}

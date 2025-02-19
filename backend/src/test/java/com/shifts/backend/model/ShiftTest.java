package com.shifts.backend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ShiftTest {
    ArrayList<Double> availability = new ArrayList<>();
    Employee employee = new Employee(
        1,
        "John",
        "Doe",
        "john@example.com",
        "password",
        availability
        );
    TimeBlock timeBlock = new TimeBlock(
        1,
        1,
        2,
        1,
        weekDayEnum.mon);
    
    Shift shift = new Shift(
        1,
        timeBlock,
        20250101);

    @Test
    void testFillEmployeesWorkingAndAlternatives(Calendar calendar){
        shift.fillEmployeesWorkingAndAlternatives(calendar);
        assertNotNull(shift.getEmployeesWorking());
        assertNotNull(shift.getEmployeesAlternatives());
        assertEquals(shift.getEmployeesWorking().indexOf(0), employee.getEmployeeId());
    }
    //TODO: add further tests for fillEmployeesWorkingAndAlternatives

    @Test
    void testGetDate() {
        assertEquals(shift.getDate(), 20250101);
    }

    @Test
    void testGetEmployeesAlternatives() {
        //TODO: add dummy employees to shift
    }

    @Test
    void testGetEmployeesWorking() {
        ArrayList<Integer> employeesWorking = new ArrayList<>();
        employeesWorking.add(employee.getEmployeeId());
        shift.setEmployeesWorking(employeesWorking);
        assertEquals(shift.getEmployeesWorking(), 1);
    }

    @Test
    void testGetShiftId() {
        assertEquals(shift.getShiftId(), 1);
    }

    @Test
    void testGetTimeBlock() {
        assertEquals(shift.getTimeBlock(), timeBlock);
    }

    @Test
    void testHasFilledShifts() {
        //TODO: figure out logic for this one
    }

    @Test
    void testSetDate() {
        shift.setDate(20250102);
        assertEquals(shift.getDate(), 20250102);
    }

    @Test
    void testSetEmployeesAlternatives() {
        ArrayList<Integer> employeesAlternatives = new ArrayList<>();
        employeesAlternatives.add(employee.getEmployeeId());
        shift.setEmployeesAlternatives(employeesAlternatives);
        assertEquals(shift.getEmployeesAlternatives(), 1);
    }

    @Test
    void testSetEmployeesWorking() {
        ArrayList<Integer> employeesWorking = new ArrayList<>();
        employeesWorking.add(employee.getEmployeeId());
        shift.setEmployeesWorking(employeesWorking);
        assertEquals(shift.getEmployeesWorking(), 1);
    }

    @Test
    void testSetShiftId() {
        shift.setShiftId(2);
        assertEquals(shift.getShiftId(), 2);
    }

    @Test
    void testSetTimeBlock() {
        TimeBlock newTimeBlock = new TimeBlock(
            2,
            2,
            3,
            2,
            weekDayEnum.tue);
        shift.setTimeBlock(newTimeBlock);
        assertEquals(shift.getTimeBlock(), newTimeBlock);
    }
}

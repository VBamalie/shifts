package com.shifts.backend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class EmployeeTest {
    public ArrayList<Double> makeAvailability(double first, double second, double third, double fourth){
        ArrayList<Double> availability = new ArrayList<Double>();
        availability.add(first);
        availability.add(second);
        availability.add(third);
        availability.add(fourth);
        return availability;
    }
    //creates a dummy employee
    public Employee makeEmployee(){
         Employee employee = new Employee(
            1,
            "John",
            "Doe",
            "john.doe@example.com",
            "password",
            makeAvailability(1.0,2.0,3.0,4.0),
            1
        );
        Shift shift = new Shift(
            1,
            new TimeBlock(1, 1, 2, 1, weekDayEnum.mon,1),
            20250101,1
        );
        ArrayList<Shift> shifts = new ArrayList<Shift>();
        shifts.add(shift);
        employee.setShifts(shifts);
        return employee;
    }

    @Test
    void testAddShift() {
        Employee employee = makeEmployee();
        Shift shift = new Shift(
            2,
            new TimeBlock(1, 1, 2, 1, weekDayEnum.mon,1),
            20250101, 1
        );
        employee.addShift(shift);
        assert(employee.getShifts().size() == 2);
    }

    @Test
    void testGetAvailability() {
        Employee employee = makeEmployee();
        assertEquals(employee.getAvailability(), makeAvailability(1.0,2.0,3.0,4.0));
    }

    @Test
    void testGetEmail() {
        Employee employee = makeEmployee();
        assertEquals(employee.getEmail(), "john.doe@example.com");
    }

    @Test
    void testGetEmployeeId() {
        Employee employee = makeEmployee();
        assertEquals(employee.getEmployeeId(), 1);
    }

    @Test
    void testGetFirstName() {
        Employee employee = makeEmployee();
        assertEquals(employee.getFirstName(), "John");
    }

    @Test
    void testGetLastName() {
        Employee employee = makeEmployee();
        assertEquals(employee.getLastName(), "Doe");
    }

    @Test
    void testGetPassword() {
        Employee employee = makeEmployee();
        assertEquals(employee.getPassword(), "password");
    }

    @Test
    void testGetShifts() {
        Employee employee = makeEmployee();
        assertEquals(employee.getShifts().size(), 1);
    }

    @Test
    //TODO: Make logic for this test
    void testGetTimeOffRequests() {
        Employee employee = makeEmployee();
        assertEquals(employee.getTimeOffRequests().size(), 0);
    }

    @Test
    void testHoursWorkedThisWeek() {
        //TODO: Make logic for this test
    }

    @Test
    void testHoursWorkedToday() {
        //TODO: Make logic for this test
    }

    @Test
    void testIsAvailable() {
        ///TODO: Make logic for this test
    }

    @Test
    void testRemoveShift() {
        Employee employee = makeEmployee();
        assert(employee.getShifts().size()== 1);
        employee.removeShift(1);
        assert(employee.getShifts().size() == 0);
    }

    @Test
    void testSetAvailability() {
        Employee employee = makeEmployee();
        assertEquals(employee.getAvailability(), makeAvailability(1.0,2.0,3.0,4.0));
        employee.setAvailability(makeAvailability(9.0,8.0,7.0,6.0));
        assertEquals(employee.getAvailability(), makeAvailability(9.0,8.0,7.0,6.0));
    }

    @Test
    void testSetEmail() {
        Employee employee = makeEmployee();
        assertEquals(employee.getEmail(), "john.doe@example.com");
        employee.setEmail("jane.doe@example.com");
        assertEquals(employee.getEmail(), "jane.doe@example.com");
    }

    @Test
    void testSetEmployeeId() {
        Employee employee = makeEmployee();
        assertEquals(employee.getEmployeeId(), 1);
        employee.setEmployeeId(2);
        assertEquals(employee.getEmployeeId(), 2);
    }

    @Test
    void testSetFirstName() {
        Employee employee = makeEmployee();
        assertEquals(employee.getFirstName(), "John");
        employee.setFirstName("Jane");
        assertEquals(employee.getFirstName(), "Jane");
    }

    @Test
    void testSetLastName() {
        Employee employee = makeEmployee();
        assertEquals(employee.getLastName(), "Doe");
        employee.setLastName("Smith");
        assertEquals(employee.getLastName(), "Smith");
    }

    @Test
    void testSetPassword() {
        Employee employee = makeEmployee();
        assertEquals(employee.getPassword(), "password");
        employee.setPassword("newpassword");
        assertEquals(employee.getPassword(), "newpassword");
    }

    @Test
    void testSetShifts() {
        Employee employee = makeEmployee();
        assertEquals(employee.getShifts().size(), 1);
        employee.setShifts(new ArrayList<Shift>());
        assertEquals(employee.getShifts().size(), 0);
    }

    @Test
    void testSetTimeOffRequests() {
        //TODO: Make logic for this test
    }
    @Test
    void testWorkingAShiftTheyAreUnavailableFor() {
        //TODO: Make logic for this test
    }
}

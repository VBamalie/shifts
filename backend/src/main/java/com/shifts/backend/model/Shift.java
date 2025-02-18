package com.shifts.backend.model;

import java.util.ArrayList;
import java.util.Date;

public class Shift {
    private int shiftId;
    private TimeBlock timeBlock;
    private int[] employeesWorking;
    private int[] employeesAlternatives;
    private int date;//first day of the this shifts week, will have the format of yyyymmdd

    //Constructor
    //employeeWorking and employeesAlternatives will be added either manually or by autocreate method.
    public Shift(int shiftId, TimeBlock timeBlock, int date) {
        this.shiftId = shiftId;
        this.timeBlock = timeBlock;
        this.date = date;
    }

    //Getters and Setters
    public int getShiftId() {
        return shiftId;
    }
    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }
    public TimeBlock getTimeBlock() {
        return timeBlock;
    }
    public void setTimeBlock(TimeBlock timeBlock) {
        this.timeBlock = timeBlock;
    }

    //Employees working will have the employeeIds of the employees working that shift.
    public int[] getEmployeesWorking() {
        return employeesWorking;
    }
    public void setEmployeesWorking(int[] employeesWorking) {
        this.employeesWorking = employeesWorking;
    }

    //Employees working will have the employeeIds of the employees working that shift.
    public int[] getEmployeesAlternatives() {
        return employeesAlternatives;
    }
    public void setEmployeesAlternatives(int[] employeesAlternatives) {
        this.employeesAlternatives = employeesAlternatives;
    }
    public int getDate() {
        return date;
    }
    public void setDate(int date) {
        this.date = date;
    }

    ///Methods
    public boolean hasFilledShifts(){
        //TODO: Checks if the shift has filled all the required employees.
        //likely going to be: if (employeesWorking.length == timeBlock.getShiftsRequired()) then return true else return false.
        return false;
    }
    public int[] fillEmployeesWorkingAndAlternatives(){
        //TODO: runs through the list of employees within the calendar object.
        //TODO: Checks if they are available to work that shift
        //TODO: adds them to an array list of potential employees
        //TODO: Sorts the Employees by employee with the least amount of hours worked this week to the most amount
        //TODO: adds the correct amount of employees to the employeesWorking array
        //TODO: adds the remaining employees to the employeesAlternatives array
        //TODO: returns the employeesWorking array
        return employeesWorking;
    }
    public int[] fillEmployeesWorkingAndAlternatives(ArrayList<Employee> priorityEmployees){
        //TODO: runs through the list of employees provided.
        //TODO: Checks if they are available to work that shift
        //TODO: adds them to an array list of potential employees
        //TODO: Sorts the Employees by employee with the least amount of hours worked this week to the most amount
        //TODO: adds the correct amount of employees to the employeesWorking array
        //TODO: adds the remaining employees to the employeesAlternatives array
        //TODO:Checks to see if the employeesWorking array has the correct amount of employees with the hasFilledShifts method.
        //TODO:if not, redoes this method with the entire employee pool.
        //TODO: returns the employeesWorking array
        return employeesWorking;
    }
}


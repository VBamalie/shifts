package com.shifts.backend.model;

import java.util.ArrayList;

public class Calendar {
private String businessName;
    private ArrayList<Employee> Employees;
    private ArrayList<Shift> Shifts;
    private ArrayList<TimeBlock> TimeBlocks;

    /*Constructor */
    public Calendar(String businessName, ArrayList<Employee> employees, ArrayList<TimeBlock> timeBlocks) {
        businessName = businessName;
        Employees = employees;
        TimeBlocks = timeBlocks;
    }
    public Calendar(String businessName){
        businessName = businessName;
    }

    //**Getters and Setters**//
    public String getBusinessName() {
        return businessName;
    }
    public void setBusinessName(String businessName) {
        businessName = businessName;
    }
    public ArrayList<Employee> getEmployees() {
        return Employees;
    }
    public void setEmployees(ArrayList<Employee> employees) {
        Employees = employees;
    }
    public ArrayList<Shift> getShifts() {
        return Shifts;
    }
    public void setShifts(ArrayList<Shift> shifts) {
        Shifts = shifts;
    }
    public ArrayList<TimeBlock> getTimeBlocks() {
        return TimeBlocks;
    }
    public void setTimeBlocks(ArrayList<TimeBlock> timeBlocks) {
        TimeBlocks = timeBlocks;
    }

    /*Methods */
    public ArrayList<Shift> createShiftFromTimeBlock(String date){
        ArrayList<Shift> emptyShift = new ArrayList<>();
        //TODO: Create a method that makes every timeblockk within the Calendar's timeblock list use tthe createShift method
        //passes date to createShift
        //Arraylist is sorted from earliest shift to latest, monday-sunday
        return null;
    }

    public ArrayList<Shift> autofillShifts(){
        //TODO: Calendar uses createShiftFromTimeBlock to create a list of shifts
        //TODO: Calendar finds the shift with the most amount of employees required
        //TODO: Calendar uses the shift's method fillEmpOnStaffandAltEmployees to fill the shift
        //Calendar checks if there is a shift unfilled earlier than the most recently filled shift
        //TODO: if yes, Calendar moves on to the earlier shift and fills it with the overloaded version of fillEmpOnStaffandAltEmployee that takes in the Employees that are working in the most recently filled shift
        //TODO: if no, Calendar moves up through the shift list looking for the next shift that is unfilled. it takes the shift and fills it with the overloaded version of fillEmpOnStaffandAltEmployee that takes in the Employees that are working in the most recently filled shift
        //TODO: if no more shifts are found, Calendar returns the list of shifts
        return null;
    }
}

package com.shifts.backend.model;

import java.util.ArrayList;

//Calendar is a class that represents the business's calendar. It contains the list of employees that can be added to the shifts on the calendar. It also contains the timeblocks that the manager made for this business.
public class Calendar {
    //@id? @generatedValue?
    private int id;
    private String businessName;
    private ArrayList<Employee> employees;
    private ArrayList<Shift> shifts;
    private ArrayList<TimeBlock> timeBlocks;

    /*Constructor */
    public Calendar(int id, String businessName, ArrayList<Employee> employees, ArrayList<TimeBlock> timeBlocks) {
        this.id = id;
        this.businessName = businessName;
        this.employees = employees;
        this.timeBlocks = timeBlocks;
    }
    public Calendar(int id, String businessName){
        this.id = id;
        this.businessName = businessName;
    }

    //**Getters and Setters**//
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBusinessName() {
        return businessName;
    }
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
    public ArrayList<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
    public ArrayList<Shift> getShifts() {
        return shifts;
    }
    public void setShifts(ArrayList<Shift> shifts) {
        this.shifts = shifts;
    }
    public ArrayList<TimeBlock> getTimeBlocks() {
        return timeBlocks;
    }
    public void setTimeBlocks(ArrayList<TimeBlock> timeBlocks) {
        this.timeBlocks = timeBlocks;
    }

    /*Methods */
    public ArrayList<Shift> createShiftFromTimeBlock(int date){
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

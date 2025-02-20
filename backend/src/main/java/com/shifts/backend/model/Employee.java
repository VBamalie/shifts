package com.shifts.backend.model;

import java.util.ArrayList;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ArrayList<Integer> timeOffRequests;
    private ArrayList<Shift> shifts;
    private ArrayList<Double> availability;
    private int calendarId;
   
    //Constructor
    public Employee(int id) {
    }
    public Employee(
        int employeeId,
        String firstName,
        String lastName,
        String email,
        String password,
        ArrayList<Double> availability,
        int calendarId
        ) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.availability = availability;
        this.calendarId = calendarId;
    }

    //Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public ArrayList<Integer> getTimeOffRequests() {
        return timeOffRequests;
    }
    public void setTimeOffRequests(Integer timeOffRequest) {
        timeOffRequests.add(timeOffRequest);
        
    }
    public ArrayList<Shift> getShifts() {
        return shifts;
    }
    public void setShifts(ArrayList<Shift> shifts) {
        this.shifts = shifts;
    }
    public ArrayList<Double> getAvailability() {
        return availability;
    }
    public void setAvailability(ArrayList<Double> availability) {
        this.availability = availability;
    }

    //Methods
    public boolean isAvailable(int date, Enum<weekDayEnum> day, double startTime, double endTime){
            return false;
        //TODO: Checks if the employee is available for the given date, day, startTime, and endTime.
    }

    public double hoursWorkedThisWeek(int date){
        //TODO: filter the shifts with the given date and return the sum of the shifts hours
        return 0;
    }
    public double hoursWorkedToday(int date, Enum<weekDayEnum> day){
        //TODO: filter the shifts with the given date and day and return the sum of the shifts hours
        return 0;
    }
    public void addShift(Shift shift){
        //TODO: Adds the shift to the shifts ArrayList
    }
    public void removeShift( Integer shiftId){
        //TODO: Removes the shift from the shifts ArrayList
    }
    public boolean workingAShiftTheyAreUnavailableFor(Shift shift){
        //TODO: Checks if the employee is working a shift they are unavailable for
        return false;
    }
}


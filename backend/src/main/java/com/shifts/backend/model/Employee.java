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
    private ArrayList<Float> availability;

    //Constructor
    public Employee() {
    }
    public Employee(
        int employeeId,
        String firstName,
        String lastName,
        String email,
        String password,
        ArrayList<Float> availability) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.availability = availability;
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
        //TODO: Change this to adding to a date to the TimeOffRequests ArrayList

    public ArrayList<Integer> getTimeOffRequests() {
        return timeOffRequests;
    }
    //TODO: Change this to adding to a date to the TimeOffRequests ArrayList
    public void setTimeOffRequests() {
        this.timeOffRequests = timeOffRequests;
    }
    public ArrayList<Shift> getShifts() {
        return shifts;
    }
    public void setShifts(ArrayList<Shift> shifts) {
        this.shifts = shifts;
    }
    public float[] getAvailability() {
        return availability;
    }
    public void setAvailability(float[] availability) {
        this.availability = availability;
    }

    //Methods
    public boolean isAvailable(int date, Enum<weekDayEnum> day, float startTime, float endTime){
            return false;
        //TODO: Checks if the employee is available for the given date, day, startTime, and endTime.
    }

    public float hoursWorkedThisWeek(int date){
        //TODO: filter the shifts with the given date and return the sum of the shifts hours
        return 0;
    }
    public float hoursWorkedToday(int date, Enum<weekDayEnum> day){
        //TODO: filter the shifts with the given date and day and return the sum of the shifts hours
        return 0;
    }
    public void addShift(Shift shift){
        //TODO: Adds the shift to the shifts ArrayList
    }
    public void removeShift(Shift shift){
        //TODO: Removes the shift from the shifts ArrayList
    }
}


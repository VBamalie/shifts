package com.shifts.backend.model;

import java.util.ArrayList;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int calendarId;
   
    //Constructor
    public Employee(int id) {
    }
    // public Employee(
    //     int employeeId,
    //     String firstName,
    //     String lastName,
    //     String email,
    //     String password,
    //     int calendarId
    //     ) {
    //     this.employeeId = employeeId;
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    //     this.email = email;
    //     this.password = password;
    //     this.calendarId = calendarId;
    // }

    //Methods
    //FIXME: Refactor these method into a service layer?
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


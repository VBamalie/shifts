package com.shifts.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int calendarId;

    // //Methods
    // //FIXME: Refactor these method into a service layer?
    // public boolean isAvailable(int date, Enum<weekDayEnum> day, double startTime, double endTime){
    //         return false;
    //     //TODO: Checks if the employee is available for the given date, day, startTime, and endTime.
    // }

    // public double hoursWorkedThisWeek(int date){
    //     //TODO: filter the shifts with the given date and return the sum of the shifts hours
    //     return 0;
    // }
    // public double hoursWorkedToday(int date, Enum<weekDayEnum> day){
    //     //TODO: filter the shifts with the given date and day and return the sum of the shifts hours
    //     return 0;
    // }
    // public void addShift(Shift shift){
    //     //TODO: Adds the shift to the shifts ArrayList
    // }
    // public void removeShift( Integer shiftId){
    //     //TODO: Removes the shift from the shifts ArrayList
    // }
    // public boolean workingAShiftTheyAreUnavailableFor(Shift shift){
    //     //TODO: Checks if the employee is working a shift they are unavailable for
    //     return false;
    // }
}


package com.shifts.backend.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Builder
//Employee is a class that represents an employee of the business. It contains all of the employee's information as well as their availability and time off requests.
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToOne( cascade = CascadeType.ALL)
    private Calendar calendar;
    @ManyToMany
    @JoinTable(
        name = "employee_shift",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "shift_id")
    )
    private Set<Shift> shifts;//the employee table will be responsible for the joined table
    @OneToOne(mappedBy = "employee")
    private Availability availability;//the employee table will not have an availability id.
    @OneToMany(mappedBy = "employee")
    private Set<TimeOffRequest> timeOffRequests;//the employee table will not have a timeOffRequest id.
    

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
    public void removeShift( Shift shift){
        //TODO: Removes the shift from the shifts ArrayList
    }
    public boolean workingAShiftTheyAreUnavailableFor(int date){
        //TODO: Checks if the employee is working a shift they are unavailable for
        return false;
    }
}


package com.shifts.backend.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
                                             /*FIELDS */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;//TODO: make email unique
    private String password;//TODO: encrypt password

    @ManyToOne( cascade = CascadeType.PERSIST, optional = false)//calendar will persist even if the employee entity is deleted. it is required for every employee to have a calendar
    private Calendar calendar;

    @ManyToMany
    @JoinTable(
        name = "employee_shift",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "shift_id")
    )//many to many relationship will be referenced in a table called employee_shift
    @JsonIgnore//this is to prevent infinite recursion when serializing the employee object to json
    private Set<Shift> shifts;

    @OneToOne(mappedBy = "employee",  cascade = CascadeType.ALL, orphanRemoval = true)//this makes it so that the availability entity related to the employee will be deleted when the employee is deleted.
    private Availability availability;
    
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)//time off requests will be deleted when the employee is deleted.
    private List<TimeOffRequest> timeOffRequests;
    

                                                /*METHODS*/
    public boolean isAvailable(int date, Enum<WeekDayEnum> day, double startTime, double endTime){
            return false;
        //TODO: Checks if the employee is available for the given date, day, startTime, and endTime.
    }

    public double hoursWorkedThisWeek(int date){
        //TODO: filter the shifts with the given date and return the sum of the shifts hours
        return 0;
    }
    public double hoursWorkedToday(int date, Enum<WeekDayEnum> day){
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


package com.shifts.backend.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

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
    private String password;
    @Value("false")
    private Boolean isManager;
    private String passwordHash;// this is to compare the password with the password hash
    @ManyToOne( cascade = CascadeType.PERSIST, optional = false)//calendar will persist even if the employee entity is deleted. it is required for every employee to have a calendar
    private Calendar calendar;

    @ManyToMany
    @JoinTable(
        name = "employee_shift",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "shift_id")
    )//many to many relationship will be referenced in a table called employee_shift
    @JsonIgnore//this is to prevent infinite recursion when serializing the employee object to json
    private List<Shift> shifts;

    @OneToOne(mappedBy = "employee",  cascade = CascadeType.ALL, orphanRemoval = true)//this makes it so that the availability entity related to the employee will be deleted when the employee is deleted.
    private Availability availability;
    
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)//time off requests will be deleted when the employee is deleted.
    private List<TimeOffRequest> timeOffRequests;
    

                                                /*METHODS*/
    public boolean isAvailable(String date, WeekDayEnum day, double startTime, double endTime){//Checks if the employee is available for the given date, day, startTime, and endTime.
            switch (day) {
                case MON:
                return availability.getMon_start() <= startTime && availability.getMon_end() >= endTime;  
                case TUE:
                return availability.getTue_start() <= startTime && availability.getTue_end() >= endTime;
                case WED:
                return availability.getWed_start() <= startTime && availability.getWed_end() >= endTime;
                case THU:
                return availability.getThu_start() <= startTime && availability.getThu_end() >= endTime;
                case FRI:
                return availability.getFri_start() <= startTime && availability.getFri_end() >= endTime;
                case SAT:
                return availability.getSat_start() <= startTime && availability.getSat_end() >= endTime;
                case SUN:
                return availability.getSun_start() <= startTime && availability.getSun_end() >= endTime;
                default:
                    return false;
            }
        
    }
    public double hoursWorkedThisWeek(String date){//filter the shifts with the given date and return the sum of the shifts hours
        int counter = 0;
        for(Shift shift: shifts){
            if(shift.getFirstDate().equals(date)){
                double hours = shift.getTimeBlock().getEndTime() - shift.getTimeBlock().getStartTime();
                counter += hours;
            }
            
        }
        return counter;    
    }
    public double hoursWorkedToday(String date, Enum<WeekDayEnum> day){
        int counter = 0;
        for(Shift shift: shifts){
            if(shift.getFirstDate() == date && shift.getTimeBlock().getWeekDayEnum() == day){
                double hours = shift.getTimeBlock().getEndTime() - shift.getTimeBlock().getStartTime();
                counter += hours;
            }
        }
        return counter;
    }
    public boolean workingAShiftTheyAreUnavailableFor(int date){
        //TODO: Checks if the employee is working a shift they are unavailable for
        return false;
    }
}


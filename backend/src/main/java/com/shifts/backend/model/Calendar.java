package com.shifts.backend.model;
//FIXME: Unable to delete calendar when there are any shifts, employees or timeblocks associated with it.
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Calendar is a class that represents the business's calendar. 
//It contains all employees that work for this company as well as all shifts that have been created and all timeblocks for the weekly schedule.
//Employee, timeblocks, and shifts will only be accessble in the server side for the autocreate function. client side will access all Employees, TimeBlocks, and Shifts through their respective controllers
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String businessName;
    @OneToMany(mappedBy = "calendar")
    @JsonIgnore
    private List<Employee> employees;
    @OneToMany(mappedBy = "calendar")
    @JsonIgnore
    private List<TimeBlock> timeBlocks;
    @JsonIgnore
    @OneToMany(mappedBy = "calendar")
    private List<Shift> shifts;

    /*Methods */
    //FIXME: Refactor this method into the service layer
    public List<Shift> autofillShifts(){
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

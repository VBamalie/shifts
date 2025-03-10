package com.shifts.backend.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//Calendar is a class that represents the business's calendar. It contains all employees that work for this company as well as all shifts that have been created and all timeblocks for the weekly schedule.
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
    @JsonBackReference
    private List<Employee> employees;
    @OneToMany(mappedBy = "calendar")
    @JsonIgnore
    private List<TimeBlock> timeBlocks;
    @JsonIgnore
    @OneToMany(mappedBy = "calendar")
    private List<Shift> shifts;

    /*Methods */
    public List<Shift> createShiftFromTimeBlock(int date){
        List<Shift> emptyShifts = new ArrayList<>();
        //TODO: Create a method that makes every timeblockk within the Calendar's timeblock list use tthe createShift method
        //passes date to createShift
        //Arraylist is sorted from earliest shift to latest, monday-sunday
        return null;
    }
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

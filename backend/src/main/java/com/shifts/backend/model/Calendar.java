package com.shifts.backend.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

//Calendar is a class that represents the business's calendar. It contains the list of employees that can be added to the shifts on the calendar. It also contains the timeblocks that the manager made for this business.
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calendar {
    //@id? @generatedValue?
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String businessName;
    @OneToMany(mappedBy = "calendar")
    private Set<Employee> employees;
    @OneToMany(mappedBy = "calendar")
    private Set<TimeBlock> timeBlocks;
    @OneToMany(mappedBy = "calendar")
    private Set<Shift> shifts;

    /*Methods */
    //FIXME: Refactor these method into a service layer?
    public Set<Shift> createShiftFromTimeBlock(int date){
        Set<Shift> emptyShifts = new HashSet<>();
        //TODO: Create a method that makes every timeblockk within the Calendar's timeblock list use tthe createShift method
        //passes date to createShift
        //Arraylist is sorted from earliest shift to latest, monday-sunday
        return null;
    }
    //FIXME: Refactor this method into the service layer
    public Set<Shift> autofillShifts(){
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

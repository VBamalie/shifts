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

}

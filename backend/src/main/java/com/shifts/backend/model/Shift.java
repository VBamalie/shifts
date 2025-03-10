package com.shifts.backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//Shift is a class that represents a shift.
//One entity will contain the timeblock that it was created from, the first date of this shift's week, the calendar that the shift belongs to, and the employees that are working the shift.
//This class also has a many to many relationship with the employee class.
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)//timeblock will persist even if the shift entity is deleted. it is required for every shift to have a timeblock
    private TimeBlock timeBlock;
    private int firstDate;//first day of the this shifts week, will have the format of yyyymmdd
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)//calendar will persist even if the shift entity is deleted. it is required for every shift to have a calendar
    private Calendar calendar;
    @ManyToMany(mappedBy = "shifts")//many to many relationship will be referenced in a table called employee_shift
    List<Employee> employeesWorking;




    ///Methods
    public boolean hasFilledShifts(){
        //TODO: Checks if the shift has filled all the required employees.
        //likely going to be: if (employeesWorking.length == timeBlock.getShiftsRequired()) then return true else return false.
        return false;
    }
    public ArrayList<Integer> fillEmployeesWorkingAndAlternatives(Calendar calendar){
        //TODO: runs through the list of employees within the calendar object.
        //TODO: Checks if they are available to work that shift
        //TODO: adds them to an array list of potential employees
        //TODO: Sorts the Employees by employee with the least amount of hours worked this week to the most amount
        //TODO: adds the correct amount of employees to the employeesWorking array
        //TODO: adds the remaining employees to the employeesAlternatives array
        //TODO: returns the employeesWorking array
        return null;
    }
    public ArrayList<Integer> fillEmployeesWorkingAndAlternatives(ArrayList<Employee> priorityEmployees){
        //TODO: runs through the list of employees provided.
        //TODO: Checks if they are available to work that shift
        //TODO: adds them to an array list of potential employees
        //TODO: Sorts the Employees by employee with the least amount of hours worked this week to the most amount
        //TODO: adds the correct amount of employees to the employeesWorking array
        //TODO: adds the remaining employees to the employeesAlternatives array
        //TODO:Checks to see if the employeesWorking array has the correct amount of employees with the hasFilledShifts method.
        //TODO:if not, redoes this method with the entire employee pool.
        //TODO: returns the employeesWorking array
        return null;
    }
}


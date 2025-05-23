package com.shifts.backend.model;

import java.util.ArrayList;
import java.util.Collections;
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
// Shift is a class that represents a shift.
// One entity will contain the timeblock that it was created from, the first
// date of this shift's week, the calendar that the shift belongs to, and the
// employees that are working the shift.
// This class also has a many to many relationship with the employee class.
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false) // timeblock will persist even if the shift entity is
                                                                // deleted. it is required for every shift to have a
                                                                // timeblock
    private TimeBlock timeBlock;
    private String firstDate;// first day of the this shifts week, will have the format of yyyymmdd
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false) // calendar will persist even if the shift entity is
                                                                // deleted. it is required for every shift to have a
                                                                // calendar
    private Calendar calendar;
    @ManyToMany(mappedBy = "shifts") // many to many relationship will be referenced in a table called employee_shift
    List<Employee> employeesWorking;

    /// Methods

    public List<Employee> fillEmployeesWorkingAndAlternatives(List<Employee> employees) {
        List<Employee> employeesPotentiallyWorking = new ArrayList<>();
        List<Employee> employeesToAdd = new ArrayList<>();
        for (Employee employee : employees) {// runs through the list of employees
            if (employee.isAvailable(firstDate, timeBlock.getWeekDayEnum(), timeBlock.getStartTime(),
                    timeBlock.getEndTime())) {// Checks if they are available to work that shift
                employeesPotentiallyWorking.add(employee);// adds them to an array list of potential employees
            }
        }
        Collections.sort(employeesPotentiallyWorking,
                (a, b) -> Double.compare(a.hoursWorkedThisWeek(firstDate), b.hoursWorkedThisWeek(firstDate)));// sorts the employees based on how many hours they have worked this week
        for (int i = 0; i < timeBlock.getShiftsRequired() && i < employeesPotentiallyWorking.size(); i++) {// runs through the amount of shifts required and the amount of employees that are potentially working
            employeesToAdd.add(employeesPotentiallyWorking.get(i));// adds the employee to the employeesWorking array
        }
        return employeesToAdd;
    }

    public List<Employee> fillEmployeesWorkingAndAlternatives(List<Employee> employees, List<Employee> priorityEmployees) {
        List<Employee> employeesToAdd = new ArrayList<>();
        List<Employee> employeesPotentiallyWorking = new ArrayList<>();
        for (Employee employee : priorityEmployees) {// runs through the list of employees
            if (employee.isAvailable(firstDate, timeBlock.getWeekDayEnum(), timeBlock.getStartTime(),
                    timeBlock.getEndTime())) {// Checks if they are available to work that shift
                employeesPotentiallyWorking.add(employee);// adds them to an array list of potential employees
            }
        }
        Collections.sort(employeesPotentiallyWorking,
                (a, b) -> Double.compare(a.hoursWorkedThisWeek(firstDate), b.hoursWorkedThisWeek(firstDate)));//sorts the employees by the least amount of hours worked this week to the most amount
        if (employeesPotentiallyWorking.size() <= timeBlock.getShiftsRequired()) {
            int numToFill = Math.min(timeBlock.getShiftsRequired(), employeesPotentiallyWorking.size());
            for (int i = 0; i < numToFill; i++) {
                employeesToAdd.add(employeesPotentiallyWorking.get(i));
            }
            return employeesToAdd;
        } else {
                    int numToFill = Math.min(timeBlock.getShiftsRequired(), employeesPotentiallyWorking.size());
                    for (int i = 0; i < numToFill; i++) {
                        Employee emp = employeesPotentiallyWorking.get(i);
                        employeesWorking.add(emp);// Assign the employee

                        employees.remove(emp);            // Remove from the remaining pool
                    }
            for (Employee employee : employees) {// runs through the list of employees
                if (employee.isAvailable(firstDate, timeBlock.getWeekDayEnum(), timeBlock.getStartTime(),
                        timeBlock.getEndTime())) {// Checks if they are available to work that shift
                    employeesPotentiallyWorking.add(employee);// adds them to an array list of potential employees
                }
            }
            Collections.sort(employeesPotentiallyWorking,
                    (a, b) -> Double.compare(a.hoursWorkedThisWeek(firstDate), b.hoursWorkedThisWeek(firstDate)));// sorts the employees by the least amount of hours worked this week to the most amount

            for (int i = 0; i < timeBlock.getShiftsRequired() && i < employeesPotentiallyWorking.size(); i++) {// runs through the amount of employees required for the shift
                employeesToAdd.add(employeesPotentiallyWorking.get(i));// adds the employee to the employeesWorking array
            }
            return employeesToAdd;
        }
    }
}

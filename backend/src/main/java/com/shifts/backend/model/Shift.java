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
//Shift is a class that represents a shift.
//One entity will contain the timeblock that it was created from, the first date of this shift's week, the calendar that the shift belongs to, and the employees that are working the shift.
//This class also has a many to many relationship with the employee class.
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)//timeblock will persist even if the shift entity is deleted. it is required for every shift to have a timeblock
    private TimeBlock timeBlock;
    private String firstDate;//first day of the this shifts week, will have the format of yyyymmdd
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)//calendar will persist even if the shift entity is deleted. it is required for every shift to have a calendar
    private Calendar calendar;
    @ManyToMany(mappedBy = "shifts")//many to many relationship will be referenced in a table called employee_shift
    List<Employee> employeesWorking;




    ///Methods
   
    public List<Employee> fillEmployeesWorkingAndAlternatives(List<Employee> employees){
        List<Employee> employeesPotentiallyWorking = new ArrayList<>();
        for (Employee employee : employees) {//runs through the list of employees 
            if(employee.isAvailable(firstDate, timeBlock.getWeekDayEnum(), timeBlock.getStartTime(), timeBlock.getEndTime())){// Checks if they are available to work that shift
                employeesPotentiallyWorking.add(employee);// adds them to an array list of potential employees
            }
        }
        Collections.sort(employeesPotentiallyWorking, (a,b)-> Double.compare(a.hoursWorkedThisWeek(firstDate), b.hoursWorkedThisWeek(firstDate)));//sorts the employees by the least amount of hours worked this week to the most amount
        for(int i = timeBlock.getShiftsRequired(); i > 0; i--){//runs through the amount of employees required for the shift
            employeesWorking.add(employeesPotentiallyWorking.get(i));//adds the employee to the employeesWorking array
        }
        return employeesWorking;
    }
    public List<Employee> fillEmployeesWorkingAndAlternatives(List<Employee> employees, List<Employee> priorityEmployees){
        List<Employee> employeesPotentiallyWorking = new ArrayList<>();
        for (Employee employee : priorityEmployees) {//runs through the list of employees 
            if(employee.isAvailable(firstDate, timeBlock.getWeekDayEnum(), timeBlock.getStartTime(), timeBlock.getEndTime())){// Checks if they are available to work that shift
                employeesPotentiallyWorking.add(employee);// adds them to an array list of potential employees
            }
        }
        Collections.sort(employeesPotentiallyWorking, (a,b)-> Double.compare(a.hoursWorkedThisWeek(firstDate), b.hoursWorkedThisWeek(firstDate)));//sorts the employees by the least amount of hours worked this week to the most amount
        if(employeesPotentiallyWorking.size()<=timeBlock.getShiftsRequired()){
            for(int i = timeBlock.getShiftsRequired(); i > 0; i--){//runs through the amount of employees required for the shift
                employeesWorking.add(employeesPotentiallyWorking.get(i));//adds the employee to the employeesWorking array
            }
            return employeesWorking;
        } else{
            for(int i = employeesPotentiallyWorking.size(); i > 0; i--){//runs through the amount of employees required for the shift
                employeesWorking.add(employeesPotentiallyWorking.get(i));//adds the employee to the employeesWorking array
                employeesPotentiallyWorking.remove(i);//removes the employee from the employeesPotentiallyWorking array
                employees.remove(employeesPotentiallyWorking.get(i));//removes the employee from the next sweep of potential employees working
            }
            return fillEmployeesWorkingAndAlternatives(employees);
        }
    }
}


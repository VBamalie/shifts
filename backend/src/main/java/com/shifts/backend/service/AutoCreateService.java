package com.shifts.backend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifts.backend.model.Calendar;
import com.shifts.backend.model.Employee;
import com.shifts.backend.model.Shift;
import com.shifts.backend.model.WeekDayEnum;
import com.shifts.backend.repository.CalendarRepo;
import com.shifts.backend.repository.EmployeeRepo;
import com.shifts.backend.repository.ShiftRepo;
import com.shifts.backend.service.service.EmployeeService;

@Service
public class AutoCreateService {

    
    private final ShiftRepo shiftRepo;
    private final CalendarRepo calendarRepo;
    private final EmployeeRepo employeeRepo;
    private final EmployeeService employeeService;

    AutoCreateService(ShiftRepo shiftRepo, CalendarRepo calendarRepo, EmployeeRepo employeeRepo, EmployeeService employeeService) {
        this.shiftRepo = shiftRepo;
        this.calendarRepo = calendarRepo;
        this.employeeRepo = employeeRepo;
        this.employeeService = employeeService;
    }


    public boolean autoCreate(long id, String date) {
        List<Shift> shifts = shiftRepo.findByCalendarAndFirstDate(calendarRepo.findById(id).get(), date);//isolate this week's shifts
        List<WeekDayEnum> weekDay = List.of(WeekDayEnum.values());//have a list of days
        for (WeekDayEnum day : weekDay) {//for each day of the week
                List<Shift> shiftsByDay = new ArrayList<>(shifts.stream().filter( shift -> shift.getTimeBlock().getWeekDayEnum() == day).toList());//isolate the shifts for that day
                boolean employeesHaveBeenAssigned = false;
                for(Shift shift : shiftsByDay) {
                    if(shift.getEmployeesWorking().size() > 0) {
                        employeesHaveBeenAssigned = true;
                        return false;
                    }
                }
                if (employeesHaveBeenAssigned == false) {//if no employees have been assigned to any shifts for this day
                shiftsByDay.sort(Comparator.comparingInt((Shift s) -> s.getTimeBlock().getShiftsRequired()).reversed());//sort the shifts by the most amount of employees required
                Shift mostRequiredEmployeesShift = shiftsByDay.get(0);//get the shift with the most amount of employees required 
                List<Employee> employees = new ArrayList<>(employeeRepo.findByCalendar(calendarRepo.findById(id).get()));//get all employees
                Collections.shuffle(employees);//shuffle the employees
                List<Employee> filledEmployees = new ArrayList<>(mostRequiredEmployeesShift.fillEmployeesWorkingAndAlternatives(employees));//fill the shift with employees;
                for(Employee employee : filledEmployees){
                    employeeService.addShift(employee.getId(), mostRequiredEmployeesShift.getId());
                }
                shiftsByDay.sort(Comparator.comparingDouble(s -> s.getTimeBlock().getStartTime()));//sort the shift by time block
                for(int i = shiftsByDay.indexOf(mostRequiredEmployeesShift)- 1; i>=0; i-- ){//move backwards through the day's shifts starting with the first shift filled until there are no more shifts to fill
                filledEmployees = shiftsByDay.get(i).fillEmployeesWorkingAndAlternatives(employees, filledEmployees);//fill the shift with a priority of the employees that are already working today
                 for (Employee employee : filledEmployees) {
                    employeeService.addShift(employee.getId(), shiftsByDay.get(i).getId());
                 }
                } 
                for (int i = shiftsByDay.indexOf(mostRequiredEmployeesShift) + 1; i < shiftsByDay.size(); i++) {
                    filledEmployees = shiftsByDay.get(i).fillEmployeesWorkingAndAlternatives(employees, filledEmployees);
                    for (Employee employee : filledEmployees) {
                        employeeService.addShift(employee.getId(), shiftsByDay.get(i).getId());
                    }
                }
            }
        }
            return true;
        }
}

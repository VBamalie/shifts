package com.shifts.backend.service;

import java.util.Collections;
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

@Service
public class AutoCreateService {

    
    private final ShiftRepo shiftRepo;
    private final CalendarRepo calendarRepo;
    private final EmployeeRepo employeeRepo;

    AutoCreateService(ShiftRepo shiftRepo, CalendarRepo calendarRepo, EmployeeRepo employeeRepo) {
        this.shiftRepo = shiftRepo;
        this.calendarRepo = calendarRepo;
        this.employeeRepo = employeeRepo;
    }


    public List<Shift> autoCreate(long id, String date) {
        List<Shift> shifts = shiftRepo.findByCalendarAndFirstDate(calendarRepo.findById(id).get(), date);//isolate this week's shifts
        List<WeekDayEnum> weekDay = List.of(WeekDayEnum.values());//have a list of days
        for (WeekDayEnum day : weekDay) {//for each day of the week
                List<Shift> shiftsByDay = shifts.stream().filter( shift -> shift.getTimeBlock().getWeekDayEnum() == day).toList();//isolate the shifts for that day
                Collections.sort(shiftsByDay, (a, b) -> Integer.compare(b.getTimeBlock().getShiftsRequired(), a.getTimeBlock().getShiftsRequired()));//sort the shifts by the most amount of employees required
                Shift mostRequiredEmployeesShift = shiftsByDay.get(0);//get the shift with the most amount of employees required 
                List<Employee> employees = employeeRepo.findByCalendar(calendarRepo.findById(id).get());//get all employees
                List<Employee> filledEmployees = mostRequiredEmployeesShift.fillEmployeesWorkingAndAlternatives(employees);//fill the shift with employees;
                Collections.sort(shiftsByDay, (a,b)-> Double.compare(a.getTimeBlock().getStartTime(), b.getTimeBlock().getStartTime()));//sort the shift by time block
                for(int i = shiftsByDay.indexOf(mostRequiredEmployeesShift)- 1; i<0; i-- ){//move backwards through the day's shifts starting with the first shift filled until there are no more shifts to fill
                 filledEmployees = shiftsByDay.get(i).fillEmployeesWorkingAndAlternatives(employees, filledEmployees);//fill the shift with a priority of the employees that are already working today
                } 
                for(int i = shiftsByDay.indexOf(mostRequiredEmployeesShift)+ 1; i<shiftsByDay.size(); i++ ){//move forwards through the day's shifts starting with the last shift filled until there are no more shifts to fill
                filledEmployees = shiftsByDay.get(i).fillEmployeesWorkingAndAlternatives(employees, filledEmployees);//fill the shift with a priority of the employees that are already working today
                }
            }
            return shifts;
        }
}

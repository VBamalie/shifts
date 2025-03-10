package com.shifts.backend.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.shifts.backend.model.Employee;
import com.shifts.backend.model.Shift;
import com.shifts.backend.repository.CalendarRepo;
import com.shifts.backend.repository.EmployeeRepo;
import com.shifts.backend.service.service.EmployeeService;

@Service
///Crud operations for the Employee class. 
///This class will also handle adding employees to a shift and adding time off requests.
///It will also handl some analytics the front end will use to display some flags for employees working over hours and outside of their availability.
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final CalendarRepo calendarRepo;

    EmployeeServiceImpl(EmployeeRepo employeeRepo, CalendarRepo calendarRepo){
        this.employeeRepo = employeeRepo;
        this.calendarRepo = calendarRepo;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Override
    public List<Employee> getAllEmployeesByCalendarId(Long calendarId) {
        return calendarRepo.findById(calendarId)
                .map(employeeRepo::findByCalendar)
                .orElseThrow(() -> new RuntimeException("Calendar not found with id: " + calendarId));
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        return employeeRepo.findById(id)
            .map(db -> {
                if(Objects.nonNull(employee.getFirstName()) && !"".equalsIgnoreCase(employee.getFirstName())){
                    db.setFirstName(employee.getFirstName());
                }
                if(Objects.nonNull(employee.getLastName()) && !"".equalsIgnoreCase(employee.getLastName())){
                    db.setLastName(employee.getLastName());
                }
                if(Objects.nonNull(employee.getEmail()) && !"".equalsIgnoreCase(employee.getEmail())){
                    db.setEmail(employee.getEmail());
                }
                if(Objects.nonNull(employee.getPassword()) && !"".equalsIgnoreCase(employee.getPassword())){
                    db.setPassword(employee.getPassword());
                }
                return employeeRepo.save(db);
            })
            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Override
    public String deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
        return "Employee deleted successfully";
    }

    @Override
    public boolean isAvailable(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAvailable'");
    }

    @Override
    public int hoursWorkedThisWeek(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hoursWorkedThisWeek'");
    }

    @Override
    public int hoursWorkdToday(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hoursWorkdToday'");
    }

    @Override
    public Employee addShift(Long id, Shift shift) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addShift'");
    }

    @Override
    public Employee removeShift(Long id, Shift shift) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeShift'");
    }
}

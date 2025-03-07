package com.shifts.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifts.backend.model.Employee;
import com.shifts.backend.repository.CalendarRepo;
import com.shifts.backend.repository.EmployeeRepo;
import com.shifts.backend.service.service.EmployeeService;

@Service
///Crud operations for the Employee class as well as some extra data found through 
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private CalendarRepo calendarRepo;

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
        return employeeRepo.findById(id).get();
    }

    @Override
    public List<Employee> getAllEmployeesByCalendarId(Long calendarId) {
        return employeeRepo.findByCalendar(calendarRepo.findById(calendarId).get());
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEmployee'");
    }

    @Override
    public void deleteEmployee(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEmployee'");
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


}

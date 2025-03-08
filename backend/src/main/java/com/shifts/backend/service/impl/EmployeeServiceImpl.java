package com.shifts.backend.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifts.backend.model.Calendar;
import com.shifts.backend.model.Employee;
import com.shifts.backend.model.Shift;
import com.shifts.backend.model.TimeOffRequest;
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

    @Override
    public Employee addTimeOffRequest(Long id, TimeOffRequest timeOffRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTimeOffRequest'");
    }

    @Override
    public Employee removeTimeOffRequest(Long id, TimeOffRequest timeOffRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeTimeOffRequest'");
    }


}

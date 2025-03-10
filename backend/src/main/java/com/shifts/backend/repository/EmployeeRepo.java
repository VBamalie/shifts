package com.shifts.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shifts.backend.model.Calendar;
import com.shifts.backend.model.Employee;

//this interface is used to access the database with the help of JPA
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findByCalendar(Calendar calendar);//this method is used to find all employees by calendar it is created by the spring framework using method name conventions
}

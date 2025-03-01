package com.shifts.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shifts.backend.model.Employee;

//provides access to the database for the Employee table
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}

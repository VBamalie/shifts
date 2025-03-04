package com.shifts.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shifts.backend.model.Shift;

//this interface is used to access the database with the help of JPA
@Repository
public interface ShiftRepo extends JpaRepository<Shift, Long> {

}

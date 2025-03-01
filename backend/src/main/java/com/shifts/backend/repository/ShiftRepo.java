package com.shifts.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shifts.backend.model.Shift;

//provides access to the database for the Shift table
public interface ShiftRepo extends JpaRepository<Shift, Integer> {

}

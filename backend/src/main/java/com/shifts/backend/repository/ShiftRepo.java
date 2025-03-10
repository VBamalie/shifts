package com.shifts.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shifts.backend.model.Calendar;
import com.shifts.backend.model.Shift;
import com.shifts.backend.model.TimeBlock;

//this interface is used to access the database with the help of JPA
@Repository
public interface ShiftRepo extends JpaRepository<Shift, Long> {


    List<Shift> findByCalendar(Calendar calendarId);

    Shift findByTimeBlock(TimeBlock timeBlock);

}

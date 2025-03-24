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


    List<Shift> findByCalendar(Calendar calendarId);//this method is used to find all shifts by calendar it is created by the jpa framework using method name conventions

    Shift findByTimeBlock(TimeBlock timeBlock);//this method is used to find a shift by time block it is created by the jpa framework using method name conventions

    List<Shift> findByCalendarAndFirstDate(Calendar calendarId, String firstDate);//this method is used to find all shifts by calendar and first date it is created by the jpa framework using method name conventions

}

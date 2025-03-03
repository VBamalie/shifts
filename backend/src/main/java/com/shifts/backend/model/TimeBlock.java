package com.shifts.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int timeBlockId;
    private double startTime;
    private double endTime;
    private int shiftsRequired;
    private Enum<weekDayEnum> weekDayEnum;
    private int calendarId;



    // //Methods
    // //FIXME: Refactor these methods into a service layer?
    // public Shift createShift(String Date){
    //     //TODO: Creates an empty shift based on the time block with the given date
    //     return null;
    // }
}


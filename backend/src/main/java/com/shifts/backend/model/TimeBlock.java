package com.shifts.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//TimeBlock is a class that represents a time block. It contains the start time, end time, the number of shifts required, the week day, and the calendar that the time block belongs to. this class is primarily used to create the shift table.
public class TimeBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double startTime;
    private double endTime;
    private int shiftsRequired;
    private weekDayEnum weekDayEnum;
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    private Calendar calendar;



    //Methods
    //FIXME: Refactor these methods into a service layer?
    public Shift createShift(String Date){
        //TODO: Creates an empty shift based on the time block with the given date
        return null;
    }
}


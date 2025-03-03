package com.shifts.backend.model;

public class TimeBlock {
    private int timeBlockId;
    private double startTime;
    private double endTime;
    private int shiftsRequired;
    private Enum<weekDayEnum> weekDayEnum;
    private int calendarId;

    //Constructor
    public TimeBlock(
        int timeBlockId, 
        int startTime, 
        int endTime, 
        int shiftsRequired, 
        Enum<weekDayEnum> weekDayEnum,
        int calendarId) {
        this.timeBlockId = timeBlockId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftsRequired = shiftsRequired;
        this.weekDayEnum = weekDayEnum;
        this.calendarId = calendarId;
    }

    //Methods
    //FIXME: Refactor these methods into a service layer?
    public Shift createShift(String Date){
        //TODO: Creates an empty shift based on the time block with the given date
        return null;
    }
}


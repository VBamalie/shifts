package com.shifts.backend.model;

public class TimeBlock {
    private int timeBlockId;
    private double startTime;
    private double endTime;
    private int shiftsRequired;
    private Enum<weekDayEnum> weekDayEnum;

    //Constructor
    public TimeBlock(
        int timeBlockId, 
        int startTime, 
        int endTime, 
        int shiftsRequired, 
        Enum<weekDayEnum> weekDayEnum) {
        this.timeBlockId = timeBlockId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftsRequired = shiftsRequired;
        this.weekDayEnum = weekDayEnum;
    }

    //Getters and Setters
    public int getShiftsRequired() {
        return shiftsRequired;
    }
    public void setShiftsRequired(int shiftsRequired) {
        this.shiftsRequired = shiftsRequired;
    }
    public int getTimeBlockId() {
        return timeBlockId;
    }
    public void setTimeBlockId(int timeBlockId) {
        this.timeBlockId = timeBlockId;
    }
    public double getStartTime() {
        return startTime;
    }
    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }
    public double getEndTime() {
        return endTime;
    }
    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }
    public Enum<weekDayEnum> getWeekDayEnum() {
        return weekDayEnum;
    }
    public void setWeekDayEnum(Enum<weekDayEnum> weekDayEnum) {
        this.weekDayEnum = weekDayEnum;
    }

    //Methods
    public Shift createShift(String Date){
        //TODO: Creates an empty shift based on the time block with the given date
        return null;
    }
}


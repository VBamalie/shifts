package com.shifts.backend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TimeBlockTest {
    TimeBlock timeBlock = new TimeBlock(1, 1, 2, 3, weekDayEnum.mon,1);
    @Test
    void testCreateShift() {
        //test that the shift is created correctly
    }

    @Test
    void testGetEndTime() {
        assertEquals(timeBlock.getEndTime(), 2);
    }

    @Test
    void testGetShiftsRequired() {
        assertEquals(timeBlock.getShiftsRequired(), 3);
    }

    @Test
    void testGetStartTime() {
        assertEquals(timeBlock.getStartTime(), 1);
    }

    @Test
    void testGetTimeBlockId() {
        assertEquals(1, timeBlock.getTimeBlockId());
    }

    @Test
    void testGetWeekDayEnum() {
        assertEquals(weekDayEnum.mon, timeBlock.getWeekDayEnum());
    }

    @Test
    void testSetEndTime() {
        assertEquals(2, timeBlock.getEndTime());
        timeBlock.setEndTime(3);
        assertEquals(3, timeBlock.getEndTime());
    }

    @Test
    void testSetShiftsRequired() {
        assertEquals(3, timeBlock.getShiftsRequired());
        timeBlock.setShiftsRequired(4);
        assertEquals(4, timeBlock.getShiftsRequired());
    }

    @Test
    void testSetStartTime() {
        assertEquals(1, timeBlock.getStartTime());
        timeBlock.setStartTime(2);
        assertEquals(2, timeBlock.getStartTime());
    }

    @Test
    void testSetTimeBlockId() {
        assertEquals(1, timeBlock.getTimeBlockId());
        timeBlock.setTimeBlockId(2);
        assertEquals(2, timeBlock.getTimeBlockId());
    }

    @Test
    void testSetWeekDayEnum() {
        assertEquals(weekDayEnum.mon, timeBlock.getWeekDayEnum());
        timeBlock.setWeekDayEnum(weekDayEnum.tue);
        assertEquals(weekDayEnum.tue, timeBlock.getWeekDayEnum());
    }
}

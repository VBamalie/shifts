package com.shifts.backend.service.service;

import java.util.List;

import com.shifts.backend.model.TimeBlock;

public interface TimeBlockService {
    TimeBlockService saveTimeBlock(TimeBlock timeBlock);
    List<TimeBlock> getAllTimeBlocks();
    TimeBlock getTimeBlockById(int id);
    List<TimeBlock> getAllTimeBlocksByCalendarId(int calendarId);
    TimeBlock updateTimeBlock(TimeBlock timeBlock);
    void deleteTimeBlock(int id);

}

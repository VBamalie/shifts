package com.shifts.backend.service.service;

import java.util.List;

import com.shifts.backend.model.TimeBlock;

//Interface for the TimeBlock class
public interface TimeBlockService {
    TimeBlockService saveTimeBlock(TimeBlock timeBlock);
    List<TimeBlock> getAllTimeBlocks();
    TimeBlock getTimeBlockById(Long id);
    List<TimeBlock> getAllTimeBlocksByCalendarId(Long calendarId);
    TimeBlock updateTimeBlock(TimeBlock timeBlock);
    void deleteTimeBlock(Long id);

}

package com.shifts.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifts.backend.model.TimeBlock;
import com.shifts.backend.repository.TimeBlockRepo;
import com.shifts.backend.service.service.TimeBlockService;

//Crud operations for the TimeBlock class
@Service
public class TimeBlockServiceImpl implements TimeBlockService {

    @Autowired
    private TimeBlockRepo timeBlockRepo;

    @Override
    public TimeBlockService saveTimeBlock(TimeBlock timeBlock) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveTimeBlock'");
    }

    @Override
    public List<TimeBlock> getAllTimeBlocks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllTimeBlocks'");
    }

    @Override
    public TimeBlock getTimeBlockById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTimeBlockById'");
    }

    @Override
    public List<TimeBlock> getAllTimeBlocksByCalendarId(Long calendarId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllTimeBlocksByCalendarId'");
    }

    @Override
    public TimeBlock updateTimeBlock(TimeBlock timeBlock) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTimeBlock'");
    }

    @Override
    public void deleteTimeBlock(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTimeBlock'");
    }


}

package com.shifts.backend.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.shifts.backend.model.Shift;
import com.shifts.backend.model.TimeBlock;
import com.shifts.backend.repository.CalendarRepo;
import com.shifts.backend.repository.ShiftRepo;
import com.shifts.backend.repository.TimeBlockRepo;
import com.shifts.backend.service.service.TimeBlockService;

//Crud operations for the TimeBlock class
@Service
public class TimeBlockServiceImpl implements TimeBlockService {

    private TimeBlockRepo timeBlockRepo;
    private CalendarRepo calendarRepo;
    private ShiftRepo shiftRepo;

    TimeBlockServiceImpl(TimeBlockRepo timeBlockRepo, CalendarRepo calendarRepo, ShiftRepo shiftRepo) {
        this.timeBlockRepo = timeBlockRepo;
        this.calendarRepo = calendarRepo;
        this.shiftRepo = shiftRepo;
    }

    @Override
    public TimeBlock saveTimeBlock(TimeBlock timeBlock) {
        return timeBlockRepo.save(timeBlock);
    }

    @Override
    public List<TimeBlock> getAllTimeBlocks() {
        return timeBlockRepo.findAll();
    }

    @Override
    public TimeBlock getTimeBlockById(Long id) {
        return timeBlockRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("TimeBlock not found with id: " + id));
    }

    @Override
    public List<TimeBlock> getAllTimeBlocksByCalendarId(Long calendarId) {
        return timeBlockRepo.findByCalendar(calendarRepo.findById(calendarId).get());
    }

    @Override
    public TimeBlock updateTimeBlock(TimeBlock timeBlock, Long id) {
        return timeBlockRepo.findById(id)
            .map(db -> {
                if(Objects.nonNull(timeBlock.getStartTime())){
                    db.setStartTime(timeBlock.getStartTime());
                }
                if(Objects.nonNull(timeBlock.getEndTime())) {
                    db.setEndTime(timeBlock.getEndTime());
                }
                if(Objects.nonNull(timeBlock.getShiftsRequired())){
                    db.setShiftsRequired(timeBlock.getShiftsRequired());
                }
                if(Objects.nonNull(timeBlock.getWeekDayEnum())){
                    db.setWeekDayEnum(timeBlock.getWeekDayEnum());
                }
                return timeBlockRepo.save(db);
            })
            .orElseThrow(() -> new RuntimeException("TimeBlock not found with id: " + id));
    }

    @Override
    public String deleteTimeBlock(Long id) {
        timeBlockRepo.deleteById(id);
        return "TimeBlock deleted with id: " + id;
    }

    
}

package com.shifts.backend.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shifts.backend.model.Availability;
import com.shifts.backend.repository.AvailabilityRepo;
import com.shifts.backend.service.service.AvailabilityService;

@Service
//Crud operations for the Availability class. Deleting an availability will be handled by the Employee class.
public class AvailabilityServiceImpl implements AvailabilityService {
    @Autowired
    private  AvailabilityRepo availabilityRepo;

    @Override
    public Availability saveAvailability(Availability availability) {
        return availabilityRepo.save(availability);
    }

    @Override
    public List<Availability> getAllAvailabilities() {
        return availabilityRepo.findAll();
    }

    @Override
    public Availability getAvailabilityById(Long id) {
        return availabilityRepo.findById(id).get();
    }

    @Override
    public Availability updateAvailability(Availability availability, Long id) {
        return availabilityRepo.findById(id)
        .map(db -> {
            if(Objects.nonNull(availability.getMon_start())){
                db.setMon_start(availability.getMon_start());
            }
            if(Objects.nonNull(availability.getMon_end())){
                db.setMon_end(availability.getMon_end());
            }
            if(Objects.nonNull(availability.getTue_start())){
                db.setTue_start(availability.getTue_start());
            }
            if(Objects.nonNull(availability.getTue_end())){
                db.setTue_end(availability.getTue_end());
            }
            if(Objects.nonNull(availability.getWed_start())){
                db.setWed_start(availability.getWed_start());
            }
            if(Objects.nonNull(availability.getWed_end())){
                db.setWed_end(availability.getWed_end());
            }
            if(Objects.nonNull(availability.getThu_start())){
                db.setThu_start(availability.getThu_start());
            }
            if(Objects.nonNull(availability.getThu_end())){
                db.setThu_end(availability.getThu_end());
            }
            if(Objects.nonNull(availability.getFri_start())){
                db.setFri_start(availability.getFri_start());
            }
            if(Objects.nonNull(availability.getFri_end())){
                db.setFri_end(availability.getFri_end());
            }
            if(Objects.nonNull(availability.getSat_start())){
                db.setSat_start(availability.getSat_start());
            }
            if(Objects.nonNull(availability.getSat_end())){
                db.setSat_end(availability.getSat_end());
            }
            if(Objects.nonNull(availability.getSun_start())){
                db.setSun_start(availability.getSun_start());
            }
            if(Objects.nonNull(availability.getSun_end())){
                db.setSun_end(availability.getSun_end());
            }
            return availabilityRepo.save(db);
        }).orElseThrow(() -> new RuntimeException("Availability not found with id: " + id));    
    }


}

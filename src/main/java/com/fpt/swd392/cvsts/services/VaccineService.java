package com.fpt.swd392.cvsts.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.swd392.cvsts.entities.Vaccine;
import com.fpt.swd392.cvsts.repositories.VaccineRepository;

@Service
@Transactional
public class VaccineService implements IVaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public Vaccine createVaccine(Vaccine vaccine) {
        try {
            vaccine.setId(UUID.randomUUID().toString());
            vaccine.setCreatedAt(LocalDateTime.now());
            vaccine.setUpdatedAt(LocalDateTime.now());
            return vaccineRepository.save(vaccine);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while creating vaccine: " + e.getMessage());
        }
        
    }

}

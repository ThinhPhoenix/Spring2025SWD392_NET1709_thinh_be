package com.fpt.swd392.cvsts.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<Vaccine> getVaccineBasicInfo(){
        List<Object[]> results = vaccineRepository.getVaccineIdandName();
        return mapToVaccineBasicInfo(results);
    }

    public List<Vaccine> mapToVaccineBasicInfo(List<Object[]> results) {
        return results.stream().map(v -> {
            Vaccine vaccine = new Vaccine();
            vaccine.setId(v[0].toString());
            vaccine.setVaccineName(v[1].toString());
            return vaccine;
        }).collect(Collectors.toList());

    }

}

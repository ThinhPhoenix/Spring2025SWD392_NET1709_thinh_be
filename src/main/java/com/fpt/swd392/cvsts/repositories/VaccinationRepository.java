package com.fpt.swd392.cvsts.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.swd392.cvsts.entities.VaccinationRecord;

@Repository
public interface VaccinationRepository extends JpaRepository<VaccinationRecord, String>{
    Optional<VaccinationRecord> findById(String id);
    List<VaccinationRecord> findByCustomerId(String customerId);
}

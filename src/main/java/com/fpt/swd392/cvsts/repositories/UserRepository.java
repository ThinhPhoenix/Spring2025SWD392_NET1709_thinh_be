package com.fpt.swd392.cvsts.repositories;

import com.fpt.swd392.cvsts.entities.User;
import com.fpt.swd392.cvsts.entities.VaccinationRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<User> findById(String id);

    @Query("SELECT vr FROM VaccinationRecord vr WHERE vr.id = :id")
    Optional<VaccinationRecord> findVaccinationRecordById(@Param("id") String id);

    @Query("SELECT vr FROM VaccinationRecord vr WHERE vr.customerId = :customerId")
    List<VaccinationRecord> findVaccinationRecordsByUserId(@Param("customerId") String customerId);
}

package com.fpt.swd392.cvsts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.swd392.cvsts.entities.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String>{

}

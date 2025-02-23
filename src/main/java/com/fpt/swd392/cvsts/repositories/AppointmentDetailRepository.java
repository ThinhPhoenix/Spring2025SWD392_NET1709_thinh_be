package com.fpt.swd392.cvsts.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.swd392.cvsts.entities.AppointmentDetail;

@Repository
public interface AppointmentDetailRepository extends JpaRepository<AppointmentDetail, String>{

    public static void updateStatus(String appointmentId, String status) {}

    Optional<AppointmentDetail> findById(String id);

}

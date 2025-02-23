package com.fpt.swd392.cvsts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.swd392.cvsts.entities.AppointmentDetail;

@Repository
public interface AppointmentDetailRepository extends JpaRepository<AppointmentDetail, String>{

}

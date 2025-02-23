package com.fpt.swd392.cvsts.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class VaccinationAppointmentDTO {
    private String customerId;
    private String vaccinationRecordId;
    private String appointmentId;
    private String appointmentDetailId;
    private String type;
    private LocalDate scheduledDate;
    private LocalTime timeFrom;
    private String status;
    
}